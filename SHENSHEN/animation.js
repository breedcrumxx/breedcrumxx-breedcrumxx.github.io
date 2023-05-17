import 'https://flackr.github.io/scroll-timeline/dist/scroll-timeline.js';


const slide1 = document.querySelector('.slide1');
const checker = document.querySelector('.checker');
const images = document.querySelectorAll('.image');

images.forEach((image) => {
    const imageTimeline = new ViewTimeline({
        subject: image
    });

    image.animate(
        { 
            transform: ['perspective(1000px) rotateX(90deg)', 'perspective(1000px) rotateX(0deg)'],
            opacity: ['0', "1"]
        },
        { 
            duration: 1,
            timeline: imageTimeline,
            delay: .5,
            timeRange: 'enter 0% exit -50%'
        }
    );
});
