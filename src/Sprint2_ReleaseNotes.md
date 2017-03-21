What is done:
	-Displays an SVG map of the trip given in the command line in a GUI window after the "Display SVG" button is pressed.
	-Displays an itenerary for the given trip in a GUI window after the "Show Itenerary" button is pressed. 
	-SVG map has been re-scaled to enable the display of the Colorad background map in the future.
	
What is not done:
	-No additional unit tests written
	-The options checklist in the GUI is non-functional, all options are still passed through the command line.
	-2-opt and 3-opt are not implemented in the final release, all SVG files generated using 1-opt.
	-No ability for the user to select a subset of destinations.
	-No ability to display the Colorado background map in the SVG. 
	
	
Usage instructions:
	How to use the command line:
		TripCo.java -flags inputfile.csv
			Recognized flags are:
			-m for adding mileages to the map
			-i for adding IDs to the map
			-n for adding Names to the map
			
		For example, to execute the program and display the mileages and the place names, the user would type:
			TripCo.java -mn places.csv
			
		Only the labels for ID or Name can be turned on, not both! The system will only show ID labels instead 
		of both, if they are both flagged.

		The output will be in the same directory as the file given as the argument. So if the user gives a relative 
		path for the filename, the output will generated in that directory.
	
	How to use the GUI:
		The SVG map produced for the input file given in the command line arguments can be displayed using the "Display SVG"
		button on the left. The corresponding itenerary for the trip can also be displayed in the GUI by pressing the "Show
		Itenerary" button on the left. Since the options checklist is currently not functional, the displayed SVG and itenerary
		will depend entirely on what command line arguments were given.
	
References:
	-Algorithm to calculate distance between to coordinates is the 'haversine' formula taken from:
		http://www.movable-type.co.uk/scripts/latlong.html
	-Unit testing utilizes JUnit 4 with jar files downloaded from:
		http://junit.org/junit4/
	-The SVG display utalizes batik 1.8, which is an Appache licensed library and can be found at:
		https://xmlgraphics.apache.org/batik/download.html
	-Much of the code contained the the "CheckBoxList" class taken from an article written by Trevor Harmon which can be found here:
		http://www.devx.com/tips/Tip/5342

	
	
	