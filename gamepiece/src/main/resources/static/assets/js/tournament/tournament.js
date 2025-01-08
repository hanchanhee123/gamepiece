const $tournamentIcon = document.querySelectorAll('.tournament-icon');
const $iconImg = document.querySelectorAll('.icon-img');

$tournamentIcon.forEach((element, idx) => {
    element.addEventListener('mouseover', function(){
        this.style.color = '#007AFF';
        this.style.transition = 'all 0.5s';
        this.style.cursor = 'pointer';
        if(!this.children[0].classList.contains('icon-img')) return;
        $iconImg[idx-1].style.backgroundColor = '#007AFF';
		$iconImg[idx-1].firstElementChild.style.color = '#FFFFFF';
        $iconImg[idx-1].style.transition = 'all 0.5s';
    });

    element.addEventListener('mouseout', function(){
        this.style.color = '#000000';
        this.style.transition = 'all 0.5s';
        if(!this.children[0].classList.contains('icon-img')) return;
        $iconImg[idx-1].style.backgroundColor = '#F1F2F5';
        $iconImg[idx-1].firstElementChild.style.color = '#000000';
        $iconImg[idx-1].style.transition = 'all 0.5s';
    });
});