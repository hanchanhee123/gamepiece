/**
 * 
 */

const $mainList = document.getElementById('main-list');
const $mainGames = document.querySelectorAll('.main-games');
const slideCnt = $mainGames.length;
const firstEleChild = $mainList.firstElementChild;

const $div = document.createElement('div');
$div.classList.add('carousel-indicators');
for(i=0; i<slideCnt; i++){
    const $button = document.createElement("button");
    $button.setAttribute('type','button');
    $button.setAttribute('data-bs-target','#main-list');
    $button.setAttribute('data-bs-slide-to',`${i}`);

    if(!i){
        $button.classList.add('active');
        $button.setAttribute('aria-current','true');
    }

    $button.setAttribute('aria-label',`Slide ${i+1}`);
    $div.appendChild($button);
}

$mainList.insertBefore($div,firstEleChild);