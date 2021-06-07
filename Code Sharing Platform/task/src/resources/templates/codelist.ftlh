<html lang="en">
<head>
    <title>Latest</title>
    <link rel="stylesheet"
           target="_blank" href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<h1>Code List</h1>
<table>
    <tr>
        <th>Code</th>
        <th>Date(USD)</th>
    </tr>
    <#list codelist as code>
        <tr>
            <td><pre><code>${code.code}</code></pre></td>
            <td><span>${code.date}</span></td>
        </tr>
    </#list>
</table>
</body>
</html>