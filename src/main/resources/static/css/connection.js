let textLength = 0;
let text = "Welcome! Please select your role in the main app or add a new user!";
let animationApplied = false; // Flag to track if animation is already applied

function type() {
  let textChar = text.charAt(textLength++);
  let paragraph = document.getElementById("typed");
  let charElement = document.createTextNode(textChar);
  paragraph.appendChild(charElement);
  if (textLength < text.length + 1) {
    setTimeout('type()', 50);
  } else {
    text = 'Python and Web Developer - Systems Engineering Student';
  }
}

document.addEventListener("DOMContentLoaded", function () {
  //load title
    type()

  // Get the dropdown button
  let dropdownButton = document.querySelector('.custom-dropdown');

  // Get all dropdown items
  let dropdownItems = document.querySelectorAll('.dropdown-item');

  // Add event listener to each dropdown item
  dropdownItems.forEach(function(item) {
      item.addEventListener('click', function() {
          // Set the dropdown button text to the clicked item's text
          dropdownButton.textContent = this.textContent;
      });
  });
});

