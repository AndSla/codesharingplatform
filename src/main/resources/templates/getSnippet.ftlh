<html>
<head>
    <title>Code</title>
    <#include "styles.css">
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <span id="load_date">${date}</span>
    <span id="time_restriction">${time} more views allowed</span>
    <span id="views_restriction">Te code will be available for ${views} seconds</span>
    <pre id="code_snippet"><code>${code}</code></pre>
</body>
</html>