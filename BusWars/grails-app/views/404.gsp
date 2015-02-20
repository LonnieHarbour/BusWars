<!DOCTYPE html>
<html>
	<head>
		<title><g:if env="development">Not Found</g:if><g:else>Error</g:else></title>
		<g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
	</head>
	<body>
		<g:if env="development">
			<g:renderException exception="${exception}" />
		</g:if>
		<g:else>
			<ul class="errors">
				<li>The Document Requested Was Not Found</li>
			</ul>
		</g:else>
	</body>
</html>
