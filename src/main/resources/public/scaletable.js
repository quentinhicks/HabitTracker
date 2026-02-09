document.addEventListener('DOMContentLoaded', () => {
    const container = document.getElementById('habits');
    const table = document.getElementById('habitTable');

    // Base padding in px
    const basePadding = 8;

    function autoAdjustPadding() {
        const rows = table.querySelectorAll("td, th");
        let padding = basePadding;

        // Start with full padding
        rows.forEach(cell => cell.style.padding = `${padding}px`);

        // While the table is too tall, reduce padding
        while ((1.1*table.scrollHeight) > container.offsetHeight && padding > 0) {
            padding--;
            rows.forEach(cell => cell.style.padding = `${padding}px`);
        }
    }

    autoAdjustPadding();
    window.addEventListener('resize', autoAdjustPadding);

    // optional: re-run if rows are added
    const observer = new MutationObserver(autoAdjustPadding);
    observer.observe(table, { childList: true, subtree: true });
});
