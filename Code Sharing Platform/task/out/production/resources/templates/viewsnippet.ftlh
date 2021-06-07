<html> 
<head> 
    <title>Code</title>
    <link rel="stylesheet" href="/styles.css">
    <link rel="stylesheet"
          target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    </head>
<body>
<Label>Load Date<span id="load_date" class="date">${date}</span></Label>
<br>
<#if time gt 0>
<Label>Time Restriction<span id="time_restriction" class="time">${time}</span></Label>
</#if>
<br>
<#if views gt 0>
<Label>View Restriction<span id="views_restriction" class="views">${views}</span></Label>
</#if>
<pre id="code_snippet" class="code"><code>${code}</code></pre>
</body>
</html>