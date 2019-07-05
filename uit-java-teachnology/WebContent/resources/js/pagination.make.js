function isNormalInteger(str) {
    var n = Math.floor(Number(str));
    return n !== Infinity && String(n) === str && n >= 0;
}

function makePaginationById(id) {
	var itemsCount = $("#" + id).data().numberItems - 0;
	var itemsOnPage = $("#" + id).data().limit - 0;
	var totalPage = $("#" + id).data().totalPage - 0;
	var currentPage = $("#" + id).data().currentPage - 0;
	var url = new URL(window.location.href);

	if (isNormalInteger(url.searchParams.get("page"))
	&& parseInt(url.searchParams.get("page")) > totalPage) {
		url.searchParams.set("page", totalPage);
		window.location = url.href;

		return;
	}
	
	var pagination = new Pagination({
		container : $("#" + id),
		pageClickUrl : function(page) {
			let currentURL = new URL(window.location.href);
			currentURL.searchParams.set("page", page);
			return "?" + currentURL.searchParams.toString();
		},
		callPageClickCallbackOnInit : true,
		showInput : false,
		showSlider : false,
		enhancedMode : true,
		maxVisibleElements : 10,
		inputTitle : "Go to page"
	});

	if (itemsCount > 0 && totalPage != 1) {
		pagination.make(itemsCount, itemsOnPage, currentPage + "");
	}
}



