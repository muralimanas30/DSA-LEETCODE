import os
import requests
import random
import datetime

README_PATH = "README.md"
PROBLEMS_DIR = "."  # repo root
USERNAME = "muralimanas30"
API_URL = f"https://leetcode-stats-api.herokuapp.com/{USERNAME}"

def fetch_leetcode_stats():
    try:
        resp = requests.get(API_URL, timeout=10)
        if resp.status_code == 200:
            return resp.json()
        else:
            print("LeetCode API error:", resp.status_code)
    except Exception as e:
        print("Error fetching stats:", e)
    # fallback empty
    return {
        "totalSolved": 0, "easySolved": 0, "mediumSolved": 0, "hardSolved": 0, "ranking": "N/A"
    }

def get_problem_folders():
    """Find problem folders like '00001 Two Sum' with README.md."""
    folders = []
    for item in os.listdir(PROBLEMS_DIR):
        if os.path.isdir(item) and item[0].isdigit() and " " in item:
            parts = item.split(" ", 1)
            if len(parts) == 2:
                num, title = parts
                readme_path = os.path.join(item, "README.md")
                if os.path.exists(readme_path):
                    folders.append((num, title, readme_path))
    return sorted(folders, key=lambda x: int(x[0])) if folders else []

def build_progress_bar(solved, total=3000, length=30):
    done = int(length * solved / total)
    return "[" + "█" * done + "▒" * (length - done) + f"] {solved}/{total}"

def main():
    stats = fetch_leetcode_stats()
    total_solved = stats.get("totalSolved", 0)
    easy = stats.get("easySolved", 0)
    medium = stats.get("mediumSolved", 0)
    hard = stats.get("hardSolved", 0)
    rank = stats.get("ranking", "N/A")

    folders = get_problem_folders()
    problem_of_day = random.choice(folders) if folders else None
    recent = folders[-5:] if folders else []
    today = datetime.datetime.now().strftime("%Y-%m-%d")

    lines = []
    lines.append("# 📘 DSA-LEETCODE Dashboard\n")
    lines.append(f"_Last updated: **{today}**_\n")

    # Badges
    lines.append("\n<p align='center'>")
    lines.append("<img src='https://img.shields.io/badge/Language-Java%2017-orange?style=for-the-badge'/>")
    lines.append("<img src='https://img.shields.io/badge/Platform-LeetCode-yellow?style=for-the-badge'/>")
    lines.append("<img src='https://img.shields.io/badge/AutoUpdate-Daily-success?style=for-the-badge'/>")
    lines.append("</p>\n")
    lines.append("---\n")

    # Stats
    lines.append("## 🏆 LeetCode Stats\n")
    lines.append(f"- **Total Solved**: {total_solved} (Easy {easy}, Medium {medium}, Hard {hard})\n")
    lines.append(f"- **Global Rank**: {rank}\n")
    lines.append(f"\n{build_progress_bar(total_solved)}\n")
    lines.append("---\n")

    # Problem of the Day
    if problem_of_day:
        num, title, path = problem_of_day
        lines.append("## 🔥 Problem of the Day\n")
        lines.append(f"📌 [{num}. {title}]({path})\n")
        lines.append("---\n")

    # Recent submissions
    if recent:
        lines.append("## 🕒 Recent Submissions\n")
        for num, title, path in recent:
            lines.append(f"- [{num}. {title}]({path})\n")
        lines.append("---\n")

    # Visuals
    lines.append("## 📈 Visuals\n")
    lines.append("<p align='center'>")
    lines.append(f"<img src='https://streak-stats.demolab.com/?user={USERNAME}&theme=tokyonight&hide_border=true' alt='Streak'/>")
    lines.append("</p>\n")
    lines.append("<p align='center'>")
    lines.append(f"<img src='https://github-readme-activity-graph.vercel.app/graph?username={USERNAME}&theme=tokyo-night&hide_border=true' alt='Activity Graph'/>")
    lines.append("</p>\n")
    lines.append("---\n")

    lines.append("✨ Auto-generated daily by GitHub Actions ✨\n")

    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write("\n".join(lines))

if __name__ == "__main__":
    main()
