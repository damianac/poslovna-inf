$(document).ready(function(){

    var modal = document.getElementById('myModalInfoUplata');
    var modalIsplata = document.getElementById('myModalInfoIsplata');

    // Get the button that opens the modal
    var uplataButton = document.getElementById("poslednjaUplataDetaljno");
    var isplatabutton = document.getElementById("poslednjaIsplataDetaljno");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];
    var span2 = document.getElementsByClassName("close")[1];
    // When the user clicks on the button, open the modal
    uplataButton.onclick = function() {
        modal.style.display = "block";
    }

    isplatabutton.onclick = function() {
        modalIsplata.style.display = "block";
    }

    span.onclick = function(){
        modal.style.display = "none";
    }

    span2.onclick = function(){
        modalIsplata.style.display = "none";
    }
});
