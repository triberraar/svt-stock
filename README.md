# svt-stock
A small app to get stock quotes from the svt website

## build status
develop: [![build status](https://www.codeship.io/projects/6b53dc50-c1f3-0132-0dca-3632ec7395c8/status?branch=develop)](https://www.codeship.io/projects/73685)
master: [![build status](https://www.codeship.io/projects/6b53dc50-c1f3-0132-0dca-3632ec7395c8/status?branch=master)](https://www.codeship.io/projects/73685)

## Usage
To launch the application just use java -jar <jarfile>.

## Configuration
A basic configuration is packaged in the file, you can however override this configuration by providing an config file called 'svt.properties'. This file should reside in the same map as the jar file.

The following config parameters are available:

* type: <file|http>. If the type is file it will use the stub data, so no calls to SVT are done. If it is http the svt site will be queried. If an invalid value is provided http will be used.
* urls:<url1, url2>. A comma delimited list of the urls to query for data on the svt site. If an invalid value is provided it will default to http://www.svt.se/svttext/web/pages/203.html. This value is not used when using type file.
* caching.duration: <caching duration>. The duration of the caching in seconds. If an invalid is provided, it will default to 300.

The provided configuration looks like this:

	type=http
	urls=http://www.svt.se/svttext/web/pages/203.html,http://www.svt.se/svttext/web/pages/204.html
	caching.duration=300

## stub data
| Name 	| Latest price 	| Highest price | Lowest price 	|
|-------------------------------------------------------|
| namea | 12.3 			| 56.7 			| 34.1 			|
| nameb | 212.3 		| 256.7 		| 234.1 		|
