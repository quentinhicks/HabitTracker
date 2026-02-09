async function fetchWeek() {
    console.log("oooh i'm running");
    let res = await fetch("/week");
    let weekVal = await res.json();
    renderWeek(weekVal);
}

function renderWeek(weekVal) {
    const p = document.getElementById("weekP");
    p.innerHTML = "<h2>" + weekVal + "</h2>";
}

// Initial load
fetchWeek();
