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

    <#-- date przychodzi jako String z bazy danych, wobec czego parsuje go do daty uzywaja datetime
    i xs_ms_nz - co oznacza format daty z bazy danych: yyyy-MM-ddTHH:mm:ss.SSS
    nastepnie freemarker wyswietla to jako date wg formatu ze swojej konfiguracji
    patrz komentarz w configu -->

    <span id="load_date">${date?datetime.xs_ms_nz}</span><br>
    <#if views gte 0>
        <span id="views_restriction">${views} more views allowed</span><br>
    </#if>
    <#if time gte 0>
        <span id="time_restriction">The code will be available for ${time} seconds</span><br>
    </#if>
    <pre id="code_snippet"><code>${code}</code></pre>

</body>
</html>