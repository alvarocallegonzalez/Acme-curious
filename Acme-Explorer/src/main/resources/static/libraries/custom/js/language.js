$(function() {
	$('#selectLanguage-custom').on('change', function() {
		var languageValue = $(this).val();
		changeLanguageAction(languageValue);
	});
});

function changeLanguageAction(languageValue, defaultLanguage = 'en') {
	var currentHref = window.location.href;
	var url = new URL(currentHref);
	
	if (languageValue) {
        if (window.location.search.includes("language")) {
            url.searchParams.set("language", languageValue);	
        } else {
            url.searchParams.append("language", languageValue);
        }
    } else {
        url.searchParams.append("language", defaultLanguage);
    }
	
	window.location.href = url;
}