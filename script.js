document.addEventListener('DOMContentLoaded', () => {
  const items = document.querySelectorAll('.nav-item');

  items.forEach((item) => {
    item.addEventListener('click', () => {
      items.forEach((entry) => entry.classList.remove('active'));
      item.classList.add('active');
    });
  });
});
