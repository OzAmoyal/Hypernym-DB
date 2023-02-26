# Hypernym-DB
This project gets a corpus of marked noun phrases and searches for a hypernym - hyponym relationship between two noun phrases
On my testing, I ran the program on a 5GB corpus which was edited before and added a <np> and </np> tags on every Noun Phrase,
then by using String methods (to optimize search) and regex matching of 5 different Hearst Patterns we create a Database of Hypernyms and Hyponyms.
for each Hyponym we count how many times it appeared in the corpus in different patterns.

The patterns I identified are:
 ({} means optionally):

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
