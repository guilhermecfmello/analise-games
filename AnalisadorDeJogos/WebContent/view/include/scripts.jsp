<%-- 
    Document   : scripts
    Created on : 03/07/2018, 10:57:00
    Author     : dskaster
--%>

<script src="http://code.jquery.com/jquery-2.1.4.min.js" ></script>
<script>
    if (!window.jQuery) { 
        document.write('<script src="${pageContext.servletContext.contextPath}/assets/vendor/js/jquery-2.1.4.min.js"></ script>');
    }
    
</script>
<script src="${pageContext.request.contextPath}/assets/js/base.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/pieChart1.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/lineChart1.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/histogram.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha256-Sk3nkD6mLTMOF0EOpNtsIry+s1CsaqQC1rVLTAy+0yc= sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>