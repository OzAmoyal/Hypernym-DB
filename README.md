# Hypernym-DB
This Java software uses Hearst patterns, a collection of lexical and syntactic patterns, to extract hypernym-hyponym links between noun phrases. Hearst patterns are used to identify hypernymy interactions. The computer extracts hypernym-hyponym pairs from a given text corpus by identifying Hearst patterns using regular expression matching and string manipulation techniques.
On my testing, I ran the program on a 5GB corpus which was edited before and added a <np> and </np> tags on every Noun Phrase,
then by using String manipulations (to optimize search) and regex matching of 5 different Hearst Patterns it creates a Database of Hypernyms and Hyponyms.
for each Hyponym we count how many times it appeared in the corpus in different patterns.

The patterns I identified are ({} means optionally):

1. `NP {,} such as NP {, NP, ..., {and|or} NP}`.
In this pattern, the first NP is the hypernym and the NPs after the words "such as" are hyponyms.
Example: "semitic languages such as Hebrew or Arabic are composed of consonants and voyels"
semitic language ⟶ Hebrew
semitic language ⟶ Arabic

2. `such NP as NP {, NP, ..., {and|or} NP}`.
Here again, the first NP is the hypernym and the NPs after the words "as" are hyponyms.
Example: "courses taught by such lecturers as Hemi, Arie, and Hodyah are great"
lecturers ⟶ Hemi
lecturers ⟶ Arie
lecturers ⟶ Hodyah

3. `NP {,} including NP {, NP, ..., {and|or} NP}`
Here again, the first NP is the hypernym and the NPs after the words "including" are hyponyms.

4. `NP {,} especially NP {, NP, ..., {and|or} NP}`
Here again, the first NP is the hypernym and the NPs after the words "especially" are hyponyms.

5. `NP {,} which is {{an example|a kind|a class} of} NP`
Here, the first NP is the hyponym and the second in a hypernym. Example: "Object oriented programming, which is an example of a computer science course" You should accept the following: (the "," is optionally)

 - NP {,} which is NP
 - NP {,} which is an example of NP
 - NP {,} which is a kind of NP
 - NP {,} which is a class of NP

#### How to run
1. Clone the repository:

` git clone https://github.com/OzAmoyal/Hypernym-DB.git`
2. Download the corpus from [here](https://drive.google.com/drive/folders/11aVnX9r-k5iy2GafZd-o5lBBgeNRuFDN?usp=sharing)
3. Download Apache Ant from [here](https://ant.apache.org/bindownload.cgi) to use the build file to compile and run the program.
4. Open the terminal in the folder that contains the build files and the src folder and enter to compile:

``` ant compile ```

To run on a corpus and get the whole database into a file enter:

``` ant run1 -Dargs="<First argument - The directory of the corpus> <Second argument- name for the new output file>" ```

To search a lemma in the corpus to find its hypernyms enter:

``` ant run2 -Dargs="<First argument - The directory of the corpus> <Second argument - the lemma to search for>" ```


#### Run Example
An entire corpus database on a given 5GB corpus with filtering hypernyms that have less than 3 hyponyms. As you can see it resulted over 64 thousand noun phrases detected.
![image](https://user-images.githubusercontent.com/93612510/221605274-95eb25d7-552a-47a4-9996-dbe5d2ad20ac.png)

for a given lemma - I searched for Canada in the corpus and got many results :

![image](https://user-images.githubusercontent.com/93612510/221606775-cc449097-0896-4742-806f-5031d3474700.png)
As we can infer, the program used the data and identified that the best way to desribe Canada is with Hypernym "Country".


###### Credits
This program was created as part of my studies in Bar Ilan University in OOP course in 2022.
