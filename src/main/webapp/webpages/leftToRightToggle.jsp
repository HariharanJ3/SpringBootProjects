<%@ include file="/init.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/plugins.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Swipe Navigation</title>
    <style>
    
    </style>
    <script>
    currentPage = 0;
    const pages = document.querySelectorAll('.page');
    const totalPages = pages.length;

    pages[0].classList.add('active');

    let startX = 0;
    let endX = 0;

    function handleTouchStart(event) {
        startX = event.touches[0].clientX;
    }

    function handleTouchMove(event) {
        endX = event.touches[0].clientX;
    }

    function handleTouchEnd() {
        if (startX > endX + 50) {
            // Swipe left
            navigateToNextPage();
        } else if (startX < endX - 50) {
            // Swipe right
            navigateToPreviousPage();
        }
    }

    function navigateToNextPage() {
        if (currentPage < totalPages - 1) {
            pages[currentPage].classList.remove('active');
            currentPage++;
            pages[currentPage].classList.add('active');
        }
    }

    function navigateToPreviousPage() {
        if (currentPage > 0) {
            pages[currentPage].classList.remove('active');
            currentPage--;
            pages[currentPage].classList.add('active');
        }
    }

    document.addEventListener('touchstart', handleTouchStart, false);
    document.addEventListener('touchmove', handleTouchMove, false);
    document.addEventListener('touchend', handleTouchEnd, false);

    </script>
</head>
<body>
    <div class="page" id="page1">Page 1</div>
    <div class="page" id="page2">Page 2</div>
    <div class="page" id="page3">Page 3</div>

</body>
</html>
