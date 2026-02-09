async function fetchLevel() {
    console.log("oooh i'm running");
    let res = await fetch("/levels");
    let levelVal = await res.json();
    renderLevels(levelVal);
}

function renderLevels(levelVal) {
    document.getElementById("levelP").innerHTML = "<h2>" + levelVal + "</h2>";
}

// Handle new habit form
document.getElementById("levelForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    await fetch("/increaseLevel", {
        method: "POST",
        headers: { "Content-Type": "application/json" }
    });
    fetchLevel();
});

// Initial load
fetchLevel();
