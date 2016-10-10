<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>短縮URL一覧</title>
</head>
<body>
    <form action="<c:url value="/urlmappings"/>" method="POST">
        <p>
            <input name="url" type="text" placeholder="短縮したいURLを入力してね" size="50"/>
            <button type="submit">短縮URLを作る</button>
        </p>
    </form>
    <table border="1">
        <tr>
            <th>短縮したURL</th>
            <th>元のURL</th>
            <th></th>
        </tr>
        <c:forEach items="${urlMaps}" var="urlMap">
            <tr>
                <td><a href="<c:url value="/${urlMap.shortUrl}"/>"><c:out value="${urlMap.shortUrl}"/></a></td>
                <td><a href="<c:url value="${urlMap.originalUrl}"/>"><c:out value="${urlMap.originalUrl}"/></a></td>
                <td>
                    <form action="<c:url value="/urlmappings/${urlMap.shortUrl}"/>" method="POST">
                        <button type="submit">削除する</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
