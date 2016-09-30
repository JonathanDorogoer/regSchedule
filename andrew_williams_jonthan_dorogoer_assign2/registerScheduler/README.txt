CS442 Design Patterns
Fall 2016
PROJECT 2 README FILE

Due Date: Friday, September 30, 2016
Submission Date: Friday, September 30, 2016
Grace Period Used This Project: 0
Grace Period Remaining: Not sure
Author(s): Andrew Williams
	   Jonathan Dorogoer
e-mail(s): awilli64@binghamton.edu
	   jdorogo1@binghamton.edu

PURPOSE:

[
This program is designed to effeciently assign students their classes based on their specified preference. In addition this This algorithm achieves the solution with the use of multiple
threads.
]

ANT INSTRUCTIONS:

[

## To clean:
ant -buildfile src/build.xml clean

## To compile: 
ant -buildfile src/build.xml all

## To run by specifying arguments from command line [similarly for the 2nd argument and so on ...]
## We will use this to run your code
ant -buildfile src/build.xml run -Darg0=firstarg 

## To run by specifying args in build.xml (just for debugging, not for submission)
ant -buildfile src/build.xml run

## To create tarball for submission
ant -buildfile src/build.xml tarzip
]


DATA STRUCTURES:

[

Created class called student- in order to keep information regarding each particular student.
Used ArrayList in order to hold students in order of input, needed to access students in a linear fashion.
Used ArrayList in order to fill classes in a linear fashion.
]

TIME COMPLEXITY (run() method):

[


]

