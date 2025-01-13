const $tournamentIcon = document.querySelectorAll('.tournament-icon .icon-img');

$tournamentIcon.forEach((element, idx) => {
    element.addEventListener('mouseover', function(){
        this.style.cursor = 'pointer';
		console.log(this.firstElementChild);
        if(!this.classList.contains('icon-img')) return;
        this.style.color = '#FFFFFF';
        this.style.transition = 'all 0.5s';
        this.style.backgroundColor = '#007AFF';
		this.nextElementSibling.style.color = '#007AFF';
        this.style.transition = 'all 0.5s';
    });

    element.addEventListener('mouseout', function(){
        if(!this.classList.contains('icon-img')) return;
        this.style.color = '#000000';
        this.style.transition = 'all 0.5s';
        this.style.backgroundColor = '#F1F2F5';
        this.nextElementSibling.style.color = '#000000';
        this.style.transition = 'all 0.5s';
    });
});