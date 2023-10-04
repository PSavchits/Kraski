document.addEventListener('DOMContentLoaded', function () {
    const categories = document.querySelectorAll('.category');
    const childCategories = document.querySelectorAll('.child-categories');

    // Добавляем обработчик события для каждой категории
    categories.forEach((category, index) => {
        category.addEventListener('click', () => {
            childCategories.forEach((childCategory, childIndex) => {
                if (index === childIndex) {
                    childCategory.classList.toggle('active');
                } else {
                    childCategory.classList.remove('active');
                }
            });
        });
    });
})

