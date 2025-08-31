import requests
import datetime
import matplotlib.pyplot as plt

USERNAME = "muralimanas30"
REPO = "DSA-LEETCODE"

def fetch_leetcode_stats(username):
    """Fetch stats from leetcode-stats-api"""
    url = f"https://leetcode-stats-api.herokuapp.com/{username}"
    try:
        res = requests.get(url, timeout=10)
        if res.status_code == 200:
            return res.json()
    except Exception as e:
        print("Error fetching stats:", e)
    return None

def build_progress_bar(solved, total=3000, length=30):
    filled = int(length * solved / total)
    bar = "█" * filled + "▒" * (length - filled)
    return f"[{bar}] {solved}/{total}"

def generate_pie_chart(easy, medium, hard):
    """Generate a pie chart of solved problems"""
    labels = ["Easy", "Medium", "Hard"]
    values = [easy, medium, hard]
    colors = ["#4CAF50", "#FFC107", "#F44336"]

    plt.figure(figsize=(5, 5))
    plt.pie(values, labels=labels, autopct="%1.1f%%", startangle=140, colors=colors)
    plt.title("LeetCode Problems Solved Distribution")
    plt.savefig("leetcode_stats.png", bbox_inches="tight")
    plt.close()

def main():
    stats = fetch_leetcode_stats(USERNAME)

    solved = stats.get("totalSolved", 0) if stats else 0
    easy = stats.get("easySolved", 0) if stats else 0
    medium = stats.get("mediumSolved", 0) if stats else 0
    hard = stats.get("hardSolved", 0) if stats else 0
    rank = stats.get("ranking", "N/A") if stats else "N/A"

    progress = build_progress_bar(solved)

    # generate chart
    generate_pie_chart(easy, medium, hard)

    today = datetime.datetime.now().strftime("%Y-%m-%d %H:%M:%S")

    readme = f"""# 📘 DSA-LEETCODE Dashboard

<p align="center">
  <img src="https://profile-counter.glitch.me/{USERNAME}/count.svg" alt="Visitor Count" />
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Solved-{solved}-blue?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Language-Java%2017-orange?style=for-the-badge" />
  <img src="https://img.shields.io/badge/Platform-LeetCode-yellow?style=for-the-badge" />
  <img src="https://img.shields.io/badge/AutoUpdate-Daily-success?style=for-the-badge" />
</p>

---

## 📊 LeetCode Stats
- **Total Solved**: {solved} (Easy {easy} / Medium {medium} / Hard {hard})  
- **Global Rank**: {rank}
{progress}


---

## 📉 Problem Distribution
<p align="center">
  <img src="leetcode_stats.png" alt="LeetCode Stats Pie Chart" />
</p>

---

## 📈 Visual Trends
<p align="center">
  <img src="https://streak-stats.demolab.com/?user={USERNAME}&theme=tokyonight&hide_border=true" alt="Streak" />
</p>
<p align="center">
  <img src="https://github-readme-activity-graph.vercel.app/graph?username={USERNAME}&theme=tokyo-night&hide_border=true" alt="Activity Graph" />
</p>

---

## 📌 GitHub Metrics
![Stars](https://img.shields.io/github/stars/{USERNAME}/{REPO}?style=social)
![Repo Size](https://img.shields.io/github/repo-size/{USERNAME}/{REPO})

---

## ✍️ Developer Quote
<p align="center">
  <img src="https://readme-typing-svg.herokuapp.com?size=22&duration=4000&color=36BCF7&center=true&vCenter=true&width=700&lines=Code.+Debug.+Repeat.;Keep+learning,+keep+improving.;Data+Structures+%26+Algorithms+build+foundations.;Consistency+beats+motivation." alt="Typing SVG" />
</p>

---

🚀 *Generated daily by GitHub Workflows*
"""

    with open("README.md", "w", encoding="utf-8") as f:
        f.write(readme)

if __name__ == "__main__":
    main()


