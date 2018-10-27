/*
 *  Bubbling Events
 */

document.getElementById('body').addEventListener('click', () => {
    console.log('body has been clicked');
});

document.getElementById('div').addEventListener('click', () => {
    console.log('div has been clicked');
});

document.getElementById('button').addEventListener('click', () => {
    console.log('button has been clicked');
});

/*
 *  Capturing Events
 */
// function bodyClick() {
//     console.log('body has been clicked');
// }

// document.getElementById('div').addEventListener('click', () => {
//     console.log('div has been clicked')
// }, false);

// function buttonClick() {
//     console.log('button has been clicked');
// }
