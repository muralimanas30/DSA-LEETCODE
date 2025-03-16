const { execSync } = require("child_process");

try {
    console.log("📥 Running processSubmission.js...");
    const commitMessage = execSync("node processSubmission.js", { encoding: "utf-8" }).trim();

    if (!commitMessage) {
        console.error("❌ Error: Commit message is empty. Aborting push.");
        process.exit(1);
    }

    console.log("🚀 Running push.js...");
    execSync("node push.js", { stdio: "inherit" });

    console.log("🎉 All done! Solution pushed successfully.");
} catch (error) {
    console.error("❌ Error in index.js:", error.message);
    process.exit(1);
}
