<html>
<head>
    <title>Latest</title>
    <#include "styles.css">
</head>
<body>
    <#list snippets as snippet>
        <span id="load_date">${snippet.date}</span>
        <pre id="code_snippet">${snippet.code}</pre>
    </#list>
</body>
</html>