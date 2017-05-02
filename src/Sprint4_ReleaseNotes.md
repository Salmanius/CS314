What is done:
	-User experience has been simplified
	-It is now possible to zoom and pan the SVG trip map
	-Google maps support has been added. Whenever a trip is generated, a corresponding KML file will generated.
	-The itinerary now displays in the GUI when "Show Itinerary" button is pressed
	-Optimization algorithms have been optimized to run much faster
	-Additional information is now displayed for searched and selected airports
	-Loading/searching for airports is now much faster
	-It is now possible to save a selected list of destinations
	
What is not done:
	-Several bugs are present.
	-No mobile support
	-Code test coverage not 100%

Usage instructions:
	How to use the command line:
		Simply type TripCo.java. All other options are handled by the GUI.

		The output will be in the same directory that the program is run in.
	
References:
	-Algorithm to calculate distance between to coordinates is the 'haversine' formula taken from:
		http://www.movable-type.co.uk/scripts/latlong.html
	-Unit testing utilizes JUnit 4 with jar files downloaded from:
		http://junit.org/junit4/
	-SQL connection in program utilizes mysql connector 6.0.6, which can be found here:
		https://dev.mysql.com/doc/relnotes/connector-j/6.0/en/news-6-0-2.html

	
	
	
