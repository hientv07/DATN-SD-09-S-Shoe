AOS.init({
    duration: 800,
    easing: 'slide',
    once: true
});

jQuery(document).ready(function ($) {

    "use strict";

    var slider = function () {
        $('.nonloop-block-3').owlCarousel({
            center: false,
            items: 1,
            loop: false,
            stagePadding: 15,
            margin: 20,
            nav: true,
            navText: ['<span class="icon-arrow_back">', '<span class="icon-arrow_forward">'],
            responsive: {
                600: {
                    margin: 20,
                    items: 2
                },
                1000: {
                    margin: 20,
                    items: 3
                },
                1200: {
                    margin: 20,
                    items: 3
                }
            }
        });
    };
    slider();


    var siteMenuClone = function () {

        $('<div class="site-mobile-menu"></div>').prependTo('.site-wrap');

        $('<div class="site-mobile-menu-header"></div>').prependTo('.site-mobile-menu');
        $('<div class="site-mobile-menu-close "></div>').prependTo('.site-mobile-menu-header');
        $('<div class="site-mobile-menu-logo"></div>').prependTo('.site-mobile-menu-header');

        $('<div class="site-mobile-menu-body"></div>').appendTo('.site-mobile-menu');


        $('.js-logo-clone').clone().appendTo('.site-mobile-menu-logo');

        $('<span class="ion-ios-close js-menu-toggle"></div>').prependTo('.site-mobile-menu-close');


        $('.js-clone-nav').each(function () {
            var $this = $(this);
            $this.clone().attr('class', 'site-nav-wrap').appendTo('.site-mobile-menu-body');
        });


        setTimeout(function () {

            var counter = 0;
            $('.site-mobile-menu .has-children').each(function () {
                var $this = $(this);

                $this.prepend('<span class="arrow-collapse collapsed">');

                $this.find('.arrow-collapse').attr({
                    'data-toggle': 'collapse',
                    'data-target': '#collapseItem' + counter,
                });

                $this.find('> ul').attr({
                    'class': 'collapse',
                    'id': 'collapseItem' + counter,
                });

                counter++;

            });

        }, 1000);

        $('body').on('click', '.arrow-collapse', function (e) {
            var $this = $(this);
            if ($this.closest('li').find('.collapse').hasClass('show')) {
                $this.removeClass('active');
            } else {
                $this.addClass('active');
            }
            e.preventDefault();

        });

        $(window).resize(function () {
            var $this = $(this),
                w = $this.width();

            if (w > 768) {
                if ($('body').hasClass('offcanvas-menu')) {
                    $('body').removeClass('offcanvas-menu');
                }
            }
        })

        $('body').on('click', '.js-menu-toggle', function (e) {
            var $this = $(this);
            e.preventDefault();

            if ($('body').hasClass('offcanvas-menu')) {
                $('body').removeClass('offcanvas-menu');
                $this.removeClass('active');
            } else {
                $('body').addClass('offcanvas-menu');
                $this.addClass('active');
            }
        })

        // click outisde offcanvas
        $(document).mouseup(function (e) {
            var container = $(".site-mobile-menu");
            if (!container.is(e.target) && container.has(e.target).length === 0) {
                if ($('body').hasClass('offcanvas-menu')) {
                    $('body').removeClass('offcanvas-menu');
                }
            }
        });
    };
    siteMenuClone();


    var sitePlusMinus = function () {
        $('.js-btn-minus').on('click', function (e) {
            e.preventDefault();
            if ($(this).closest('.input-group').find('.form-control').val() != 0) {
                $(this).closest('.input-group').find('.form-control').val(parseInt($(this).closest('.input-group').find('.form-control').val()) - 1);
            } else {
                $(this).closest('.input-group').find('.form-control').val(parseInt(0));
            }
        });
        $('.js-btn-plus').on('click', function (e) {
            e.preventDefault();
            $(this).closest('.input-group').find('.form-control').val(parseInt($(this).closest('.input-group').find('.form-control').val()) + 1);
        });
    };
    sitePlusMinus();


    var siteSliderRange = function () {
        $("#slider-range").slider({
            range: true,
            min: 0,
            max: 500,
            values: [75, 300],
            slide: function (event, ui) {
                $("#amount").val("$" + ui.values[0] + " - $" + ui.values[1]);
            }
        });
        $("#amount").val("$" + $("#slider-range").slider("values", 0) +
            " - $" + $("#slider-range").slider("values", 1));
    };
    siteSliderRange();


    var siteMagnificPopup = function () {
        $('.image-popup').magnificPopup({
            type: 'image',
            closeOnContentClick: true,
            closeBtnInside: false,
            fixedContentPos: true,
            mainClass: 'mfp-no-margins mfp-with-zoom', // class to remove default margin from left and right side
            gallery: {
                enabled: true,
                navigateByImgClick: true,
                preload: [0, 1] // Will preload 0 - before current, and 1 after the current image
            },
            image: {
                verticalFit: true
            },
            zoom: {
                enabled: true,
                duration: 300 // don't foget to change the duration also in CSS
            }
        });

        $('.popup-youtube, .popup-vimeo, .popup-gmaps').magnificPopup({
            disableOn: 700,
            type: 'iframe',
            mainClass: 'mfp-fade',
            removalDelay: 160,
            preloader: false,

            fixedContentPos: false
        });
    };
    siteMagnificPopup();


});

// *******************************ảnh********************
const dropArea = document.getElementById('drop--area');

['dragenter', 'dragover'].forEach(event => {
    dropArea.addEventListener(event, function (e) {
        e.preventDefault();
        e.stopPropagation();
        dropArea.classList.add('highlight');
    });
});

['dragleave', 'drop'].forEach(event => {
    dropArea.addEventListener(event, function (e) {
        e.preventDefault();
        e.stopPropagation();
        dropArea.classList.remove('highlight');
    });
});

dropArea.addEventListener('drop', function (e) {
    e.preventDefault();
    e.stopPropagation();
    let dt = e.dataTransfer;
    let files = dt.files;
    handleFiles(files).then(result => {
        console.log(result.children);
    });
});


function handleFilesUpdate(files) {
    return new Promise((resolve, reject) => {
        const maxAllowedFiles = 5;
        const gallery = document.getElementById('gallery');
        const existingImages = gallery.querySelectorAll('img');
        const currentImageCount = existingImages.length;
        const imgCount = files.length;
        const testImgDiv = document.getElementById('testImg');

        if (currentImageCount >= maxAllowedFiles) {
            alert('Bạn đã đạt tối đa số lượng ảnh cho phép.');
            resolve(gallery);
            return;
        }
        if (imgCount > 0) {
            testImgDiv.style.display = 'none'; // Ẩn đi thẻ <div id="testImg">
        }

        const Files = Array.from(files);
        const createFileId = length => {
            let str = '';
            for (; str.length < length; str += Math.random().toString(36).substr(2)) ;
            return str.substr(0, length);
        };

        const uploadAndPreviewFile = file => {
            if (currentImageCount + Files.length <= maxAllowedFiles) {
                Files.forEach(file => {
                    file.id = createFileId(Math.round(file.lastModified * 100) / file.lastModified);
                    uploadFile(file);
                    previewFile(file);
                });

                resolve(gallery);
            } else {
                alert('Bạn chỉ được chọn tối đa 5 ảnh.');
                resolve(gallery);
            }
        };

        uploadAndPreviewFile();
    });
}

function handleFiles(files) {
    return new Promise((resolve, reject) => {
        const maxAllowedFiles = 5;
        const gallery = document.getElementById('gallery');
        const existingImages = gallery.querySelectorAll('img');
        const currentImageCount = existingImages.length;

        if (currentImageCount >= maxAllowedFiles) {
            alert('Bạn đã đạt tối đa số lượng ảnh cho phép.');
            resolve(gallery);
            return;
        }

        const Files = Array.from(files);
        const createFileId = length => {
            let str = '';
            for (; str.length < length; str += Math.random().toString(36).substr(2)) ;
            return str.substr(0, length);
        };

        const uploadAndPreviewFile = file => {
            if (currentImageCount + Files.length <= maxAllowedFiles) {
                Files.forEach(file => {
                    file.id = createFileId(Math.round(file.lastModified * 100) / file.lastModified);
                    uploadFile(file);
                    previewFile(file);
                });

                resolve(gallery);
            } else {
                alert('Bạn chỉ được chọn tối đa 5 ảnh.');
                resolve(gallery);
            }
        };

        uploadAndPreviewFile();
    });
}

function previewFile(file) {
    const reader = new FileReader();
    //console.log('file:id', file.id)
    reader.readAsDataURL(file);
    reader.onloadend = function () {
        const img = document.createElement('img');
        const fig = document.createElement('figure');
        const spanOne = document.createElement('span');
        const spanTwo = document.createElement('span');
        const mainSpan = document.createElement('span');
        const progressSpan = document.createElement('span');
        fig.classList.add('preview');
        img.classList.add('img');
        mainSpan.classList.add('mainSpan');
        spanOne.classList.add('spanOne');
        spanTwo.classList.add('spanTwo');
        progressSpan.classList.add('progressSpan');
        progressSpan.id = file.id;
        mainSpan.onclick = function (e) {
            this.parentElement.remove();
        };
        img.src = reader.result;
        [spanOne, spanTwo].forEach(item => {
            mainSpan.appendChild(item);
        });
        [img, mainSpan, progressSpan].forEach(item => {
            fig.appendChild(item);
        });
        document.getElementById('gallery').appendChild(fig);
    };
}

function uploadFile(file) {
    const config = {
        headers: {"X-Requested-With": "XMLHttpRequest"},
        onUploadProgress: function (progressEvent) {
            let progress = Math.round(progressEvent.loaded * 100.0 / progressEvent.total);
            if (document.getElementById(`${file.id}`) !== null) {
                document.getElementById(`${file.id}`).style.height = `${100 - progress}%`;
            }
        }
    };

    //add cloudinary url
    const url = 'https://api.cloudinary.com/v1_1/accountuser-name/upload';
    const data = new FormData();
    data.append("upload_preset", "eg-preset"); //append cloudinary specific config
    data.append('file', file);
    axios.post(url, data, config).then(res => {
        if (res.data) {
            const uploadedImgData = res.data;
            const imgTag = document.getElementById(`${file.id}`).previousSibling.previousSibling;
            imgTag.src = uploadedImgData.url;
            imgTag.dataset.data = JSON.stringify(uploadedImgData);
            document.getElementById(`${file.id}`).parentElement.classList.remove('preview');
            document.getElementById(`${file.id}`).parentElement.classList.add('done');
            //console.log(imgTag);
        }
    }).catch(err => {
        console.log(err);
    });
}

function submitForm() {
    // Check if files have been selected
    var fileInput = document.getElementById("file--input");
    if (fileInput.files.length === 0) {
        // alert("Không được để ảnh trống")
        var errorMsg = document.getElementById("error-msg");
        errorMsg.textContent = "Không được để trống ảnh"
        return false
    } else {
        return true
    }
}
// *******************************ảnh end********************
