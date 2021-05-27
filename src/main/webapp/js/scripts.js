/**
 * test file
 */

function copyLink(id) {
  var copyText = document.getElementById(id);
navigator.clipboard.writeText(copyText.value);
 
}