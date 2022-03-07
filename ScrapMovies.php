<?php

require 'vendor/autoload.php';
$httpClient = new \GuzzleHttp\Client();
$response = $httpClient->get('https://imdb.com/');
$htmlString = (string) $response->getBody();
//add this line to suppress any warnings
libxml_use_internal_errors(true);
$doc = new DOMDocument();
$doc->loadHTML($htmlString);
$xpath = new DOMXPath($doc);
$titles = $xpath->evaluate('//ol[@class="row"]//li//article//h3/a');
$prices = $xpath->evaluate('//ol[@class="row"]//li//article//div[@year="xxx"]//p[@year="xxx"]');
foreach ($titles as $key => $title) {
echo $title->textContent . ' @ '. $prices[$key]->textContent.PHP_EOL;
}
/*
function scrape_imdb($year_start, $year_end, $page_start, $page_end){
    $curling = curl_init();
    $all_data = array();
    for ($page = $page_start; $page <= $page_end; $page++){
        $url = 
    }
}
*/

