import os
import re

def extract_stats(readme_path):
    runtime, memory = None, None
    with open(readme_path, "r", encoding="utf-8") as f:
        for line in f:
            if "ms" in line and runtime is None:
                runtime = line.strip()
            if "MB" in line and memory is None:
                memory = line.strip()
            if runtime and memory:
                break
    if runtime and memory:
        return f"{runtime}, {memory}"
    elif runtime:
        return runtime
    elif memory:
        return memory
    else:
        return ""

def generate_table(base_path="."):
    rows = []
    for folder in sorted(os.listdir(base_path)):
        if not os.path.isdir(folder):
            continue
        if not re.match(r"^\d{5} ", folder):
            continue

        problem_num, problem_title = folder.split(" ", 1)
        readme_path = os.path.join(base_path, folder, "README.md")

        stats = extract_stats(readme_path) if os.path.exists(readme_path) else ""
        link = f"[README](./{folder}/README.md)"
        rows.append(f"| {problem_num} | {problem_title} | {stats} | {link} |")

    header = (
        "# DSA-LEETCODE\n\n"
        "## Problem List\n\n"
        "| #     | Title | Stats | Link |\n"
        "|-------|-------|-------|------|\n"
    )
    return header + "\n".join(rows) + "\n"

def main():
    content = generate_table(".")
    with open("README.md", "w", encoding="utf-8") as f:
        f.write(content)

if __name__ == "__main__":
    main()
