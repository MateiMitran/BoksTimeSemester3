import React, { useState } from 'react';
import './Slideshow.css';
import { SliderData } from './SlideshowData';
import { FaArrowAltCircleRight, FaArrowAltCircleLeft } from 'react-icons/fa';

  const ImageSlider = ({ slides }) => {
    const [current, setCurrent] = useState(0);
    const length = slides.length;
  
    const nextSlide = () => {
      setCurrent(current === length - 1 ? 0 : current + 1);
    };
  
    const prevSlide = () => {
      setCurrent(current === 0 ? length - 1 : current - 1);
    };
  
    if (!Array.isArray(slides) || slides.length <= 0) {
      return null;
    }
  
    return (
      <section className='slidershow'>
        <FaArrowAltCircleLeft className='left-arrow' onClick={prevSlide} />
        <FaArrowAltCircleRight className='right-arrow' onClick={nextSlide} />
        {SliderData.map((slide, index) => {
          return (
            <div
              className={index === current ? 'slide active' : 'slide'}
              key={index}
            >
              {index === current && (
                
                // eslint-disable-next-line
                <a onClick = {() => {window.location.href = "weight-class?name="+slide.title}}>
                <img src={slide.image} alt='travel' className='image' witdh="500" height="500" /> 
                <h1 className="text">{slide.title}</h1>
                </a>
              )}
            </div>
          );
        })}
      </section>
    );
  };
  
  export default ImageSlider;