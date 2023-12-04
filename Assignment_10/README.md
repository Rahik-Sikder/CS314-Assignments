### Huffman Compression

Hi Casey 👋

## An analysis of my experiments.

I decided to run experiments on just how compressed a file can become. Or in other words, how many
times can a file be run through the huffman compression algorithm until it becomes larger than the
previous compression. The data from these experiments are below. I'll answer the questions from the
assignment questions below, then elaborate on anything interesting I also found.

# What kinds of file lead to lots of compressions?

Files that stored text data, ie data that's meant to be read, had around 40% compression on the 
initial compression. These included the files in the Calgary and BooksAndHTML sets. This is is 
likely because these types of files have an alphabetical frequency distribution that looks like a
decreasing exponential function. Because the shortest paths occur the most amount of time, a good
portion of the file is able to be shortened. 

# What kind of files had little or no compression?

Files that hold data that's not in an 8bit chunk like text. Or files where the frequency is just 
going to be a descreasing exponential and just generally randomized and fairly even across the file.
This would be the TIFF image files in Waterlook which likely have large, wide Huffman trees due to
there not really being 8bit chunks that occur much more frequently than other 8bit chunks.

# What happens when you try and compress a huffman code file?

Initially that huffman file becomes compressed. However accross the board, the second compression
resulted in very very marginal gains compared to the initial compression, at around 1 to 2%. The
exact compression percentages can be seen below. Eventually, if you keep compressing the files, the
compression actually turns into expansion. The larger the file the more runs it takes for this to
happen, as the Waterloo files, which started at around 12 mil bits took 4 runs to result in
expansion, whereas the smaller 10 mil BooksAndHTML and 3mil Calgary took 3 runs. BooksAndHTML was
suprising as I thought it would be similar to Waterloo due to 12 mil and 10 mil being similar in 
size. However, I belive that because it's initial compression was much more efficient than 
Waterloo's initial compression, it ended up "reaching the bottom" sooner despite their similar 
size. 

Additionally, the Huffmark program calculated total bits and not average so there could be outlier
files in either that scew the entire experiment. Ideally, I should have modified the HuffMark
program to output the average.

# Interesting Stuff

For some reason Waterloo's total compression from one run would exactly match the next run's total
run taken in. This is not really the case for Calgary or BooksAndHTML, which would make sense since
all the files are not going to perfectly be divisble by 8 and extra bits need to be added to make 
that last byte. I think the fact this isn't present in Waterloos shows how it's paths are likely
mostly the same length. Refer back to the explanation I gave for "What kind of files had little or 
no compression?"

Additionally, the timing for each compression didn't really change much from each compression. No
matter the dataset, the timing for each subsequent run would only decrease by .1 if at all. To find
noticable patterns, I'd probably have to use huge files that can really get compressed down by 
above 50%. For another time. 


## Experimental Data

Compressed each data set 4 times and recorded the HuffMark results

# Waterloo

**1st Run**
Total Bytes:        12_466_304
Total Compression:  10_205_282
Time:               13.678s
% Compressed:       *18.137* 

**2nd Run**
Total Bytes:        10_205_282
Total Compression:  10_063_357
Time:               13.010s
% Compressed:       *1.391* 

**3rd Run**
Total Bytes:        10_063_357
Total Compression:  10_062_740
Time:               12.985s
% Compressed:       *0.006* 

**4th Run**
Total Bytes:        10_062_740
Total Compression:  10_074_902
Time:               13.078s
% Compressed:       *-0.121* 


# Calgary

**1st Run**
Total Bytes:        3_251_491
Total Compression:  1_845_571
Time:               2.506s
% Compressed:       *43.239* 

**2nd Run**
Total Bytes:        1_845_571
Total Compression:  1_809_799
Time:               2.439s
% Compressed:       *1.938* 

**3rd Run**
Total Bytes:        1_815_947 <- Difference here likely due to the extra bits added
Total Compression:  1_826_435    on through compression adding up through the files
Time:               2.492s       What's interesting is why Waterloo was perfect though...
% Compressed:       *-0.579* 

**4th Run**
Total Bytes:        1_832_583 <- huge jump from 3rd Run compress!
Total Compression:  1_844_805
Time:               2.528s
% Compressed:       *-0.667* 


# Books and HTML

**1st Run**
Total Bytes:        9_788_581
Total Compression:  5_828_112
Time:               7.950s
% Compressed:       *40.460* 

**2nd Run**
Total Bytes:        5_828_112
Total Compression:  5_747_220
Time:               7.454s
% Compressed:       *1.388* 

**3rd Run**
Total Bytes:        5_753_368
Total Compression:  5_760_065
Time:               7.414s
% Compressed:       *-0.116* 

**4th Run**
Total Bytes:        5_766_213
Total Compression:  5_773_194
Time:               7.459
% Compressed:       *-0.121* 