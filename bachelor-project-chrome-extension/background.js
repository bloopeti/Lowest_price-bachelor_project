let supportedDomains = ['www.emag.ro', 'www.pcgarage.ro'];

chrome.tabs.onUpdated.addListener(function (tabId, changeInfo, tab) {
    if (changeInfo.status === "complete") {
        console.log("Just completed updating " + tab.url);
        checkSupportedDomain(tab);
    }
});

function checkSupportedDomain(tab) {
    chrome.tabs.query({ active: true, currentWindow: true }, function (tabs) {
        var url = new URL(tab.url);
        var domain = url.hostname;
        if (supportedDomains.includes(domain)) {
            console.log(domain + " is supported!");
            chrome.pageAction.show(tab.id);

            checkCheaperPrice(url)
        }
    });
}

function checkCheaperPrice(url) {
    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:9906/app/productUrl/cheapest", true);
    xhr.setRequestHeader('Content-type', 'text/plain');
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4 && xhr.status == 200) {
            console.log(xhr.responseText);
            if (!(xhr.responseText === 'No similar url found!')) {
                if (xhr.responseText.includes(url.hostname + url.pathname)) {
                    console.log("You're on the website with the cheapest price for this product!");
                    chrome.storage.sync.set({ onCheapestSite: true });
                } else {
                    console.log("This product can be found at a cheaper price at '" + xhr.responseText + "'!");
                    chrome.storage.sync.set({ cheaperPriceUrl: xhr.responseText });
                    chrome.storage.sync.set({ onCheapestSite: false });
                }
            }
        }
    }
    xhr.send(url.pathname);
}