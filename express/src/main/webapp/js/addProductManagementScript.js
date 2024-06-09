document.addEventListener("DOMContentLoaded", function() {
    fetch("/express/ProductController?action=GET_CATEGORIES")
        .then(response => response.json())
        .then(data => {
            const categorySelect = document.getElementById("category_id");
            data.forEach(category => {
                const option = document.createElement("option");
                option.value = category.category_id;
                option.text = category.category_name;
                categorySelect.appendChild(option);
            });
        })
        .catch(error => console.error("Error loading categories:", error));
});