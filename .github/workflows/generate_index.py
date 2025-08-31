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
    except Exception as e:
        print("Error fetching stats:", e)
    return {}

def get_problem_folders():
    """Get problem folders like '00001 Two Sum'."""
    folders = []
    for item in os.listdir(PROBLEMS_DIR):
        if os.path.isdir(item) and item[0].isdigit() and " " in item:
            num, title = item.split(" ", 1)
            readme_path = os.path.join(item, "README.md")
            if os.path.exists(readme_path):
                folders.append((num, title, readme_path))
    # sort by number
    return sorted(folders, key=lambda x: int(x[0]))

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

    # Problem of the day
    problem_of_day = random.choice(folders) if folders else None

    # Recent submissions = last 5
    recent = folders[-5:]

    today = datetime.datetime.now().strftime("%Y-%m-%d")

    lines = []
    lines.append("# 📘 DSA-LEETCODE Dashboard\n")
    lines.append(f"_Last updated: **{today}**_\n")
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
        lines.append(f"[{num}. {title}]({path})\n")
        lines.append("---\n")

    # Recent submissions
    lines.append("## 🕒 Recent Submissions\n")
    for num, title, path in recent:
        lines.append(f"- [{num}. {title}]({path})\n")
    lines.append("---\n")

    # Footer
    lines.append("✨ Auto-generated daily by GitHub Actions ✨\n")

    with open(README_PATH, "w", encoding="utf-8") as f:
        f.write("\n".join(lines))

if __name__ == "__main__":
    main()
