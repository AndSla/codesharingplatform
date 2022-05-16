<html>
<head>
    <title>Code</title>
    <style>
        span {
            color: green;
        }
        pre {
            border-style: solid;
            border-width: 2px;
            padding: 5px;
        }
    </style>
</head>
<body>
    <#list snippets as snippet>
        <span id="load_date">${snippet.date}</span>
        <pre id="code_snippet">${snippet.code}</pre>
    </#list>
</body>
</html>