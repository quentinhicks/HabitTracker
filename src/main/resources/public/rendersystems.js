async function SysProcess() {
    let res = await fetch("/isEvaluated");
    let isEval = await res.json();
    console.log('Am I ran? Res value ' + isEval);
    if (isEval === true) {
        console.log('RenderSystems: I\'m true...')
        document.getElementById("systemsButton").classList.add('disappear');
        fetchSystems();
    }
    else {resetSystems();}
}

// Handle new habit form
document.getElementById("systemsButton").addEventListener("submit", async (e) => {
    e.preventDefault();
    document.getElementById("systemsButton").classList.add('disappear');
    fetchSystems();
});

async function fetchSystems() {
    let res = await fetch("/systems");
    let systems = await res.json();
    let res2 = await fetch("/eval");
    let evaluation = await res2.json();
    renderSystems(systems, evaluation);

}

function renderSystems(systems, eval) {
    const tbody = document.querySelector("#systemTable tbody");
    tbody.innerHTML = "";

    for (const system in systems) {
        let row = document.createElement("tr");
        console.log(system);

        // cell for the category of system
        let catCell = document.createElement("td");
        catCell.textContent = system;

        // cell for the evaluation
        let evalCell = document.createElement("td");
        let rating = systems[system];
        let evalSymbol;
        if (rating === 3) {
            catCell.style.backgroundColor = "#ddeecd";
            evalCell.style.backgroundColor = "#ddeecd";
            evalSymbol = "☆";
        }
        if (rating === 2) {
            catCell.style.backgroundColor = "#baf0ef";
            evalCell.style.backgroundColor = "#baf0ef";
            evalSymbol = "-";
        }
        if (rating === 1) {
            catCell.style.backgroundColor = "#edddf6";
            evalCell.style.backgroundColor = "#edddf6";
            evalSymbol = "☓";
        }
        console.log(rating);
        evalCell.textContent = evalSymbol;
        row.appendChild(catCell);
        row.appendChild(evalCell);

        tbody.appendChild(row);
    }

    const p = document.querySelector("#evalP");
    if (eval === true) {
        p.innerHTML = "Great week! You're ready to level up"
    }
    if (eval === false) {
        p.innerHTML = "Ahh, I see -- rough week. I'm not going to overload you. Keep at it!"
    }
}

function resetSystems() {
    document.getElementById("systemsButton").classList.remove('disappear');
    const tbody = document.querySelector("#systemTable tbody");
    const p = document.querySelector("#evalP");
    tbody.innerHTML = ""; p.innerHTML = "";
}
