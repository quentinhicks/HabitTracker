document.getElementById("saveForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    let weekNum = document.getElementById("weekNum1").value.trim();
    if (!weekNum) return;

    await fetch("/save", {
        method: "POST",
        body: weekNum
    });

    fetchHabits();
    fetchAddHabs();
    fetchHabs();
    fetchLevel();
    fetchWeek();
    SysProcess();

    document.getElementById("weekNum1").value = "";
});

document.getElementById("loadForm").addEventListener("submit", async (e) => {
    e.preventDefault();
    let weekNum = document.getElementById("weekNum2").value.trim();
    if (!weekNum) return;

    await fetch("/load", {
        method: "POST",
        body: weekNum
    });

    fetchHabits();
    fetchAddHabs();
    fetchHabs();
    fetchLevel();
    fetchWeek();
    SysProcess();

    document.getElementById("weekNum2").value = "";
});
