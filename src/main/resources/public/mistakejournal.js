// Attach listeners once the page loads
window.addEventListener("DOMContentLoaded", () => {
    const headerCells = document.querySelectorAll("#habitTable thead th[data-day]");

    headerCells.forEach(th => {
        th.addEventListener("click", () => {
            const dayIndex = th.dataset.day; // 0 for M, 1 for T, etc.
            console.log("Clicked!");
            showMistakeEntry(dayIndex);
        });
    });
});

function getMistakes(dayIndex) {
    let phrase = fetch("/mistakes", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ dayIndex })
    });
    console.log(phrase);
    return phrase;
}

function showMistakeEntry(dayIndex) {
    const formArea = document.getElementById("mistakesTable");
    const phrase = getMistakes(dayIndex);

    let row = document.createElement("tr");
    let dayCell = document.createElement("td");
    dayCell.innerHTML = `<p>Day ${dayIndex}</p>`;
    row.appendChild(dayCell);
    formArea.appendChild(row);

    let formRow = document.createElement("tr");
    let inputCell = document.createElement("td");
    let mistakeForm = document.createElement("form");
    mistakeForm.innerHTML =
            `<textarea id="mistakeExp" placeholder="Mistake explanation" required></textarea>
            <button type="submit">Submit explanation</button>`;
    mistakeForm.classList.add(`mistakeForm`);
    mistakeForm.classList.add(`day-${dayIndex}`);
    inputCell.appendChild(mistakeForm);
    formRow.appendChild(inputCell);
    formArea.appendChild(formRow);
}
