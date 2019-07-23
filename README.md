# Liferay Code Samples

## What this is

This repository holds code samples demonstrating how to

- build applications for
- extend and enhance
- configure
- style and change the UI of
- consume services from

 Liferay Portal and other Liferay products based on Liferay Portal.

 It's is an updated and re-organized version of [liferay-blade-samples repository](https://github.com/liferay/liferay-blade-samples). It is maintained by Liferay's Developer Relations Team in collaboration with Liferay's engineers who started and maintained the `liferay-blade-samples` project.

## How it is organized

The repository has several levels deep hierarchy:

- `product`
  - `product version`
    - `java version`
      - code samples

which we explain below.

### Product

We first separate code samples per product. At the time of writing there are 3 products in this repository:

- [portal](portal) - the code samples for [Liferay Portal](https://portal.liferay.dev)
- [commerce](commerce) - the code samples for [Liferay Commerce](https://commerce.liferay.dev/)
- [other](other) - other code samples

### Product version

Then we divide the samples in each product by product version. 

APIs, extension points, configuration keys, frameworks, ... may differ significantly between product versions. It is crucial that developers can lean from a sample that is developed for and tested with the product version they use.

### Java version

On the next level we divide the samples by Java versions. 

This is to ensure the code samples are build and tested with all Java versions supported by given product version. It also allows do demonstrate the use of new features brought by newer Java versions.

### List of Code Samples

On this level developers can find the actual code samples. However each sample can be build using different build systems (Maven, Gradle, ...). To avoid duplicating the source code for each build system, the following structure is in place:

- `code`
  - `sample 1`
    - source code
    - other common files
  - `sample 2`
    - source code
    - other common files
  - `sample ...`
- `build system A`
  - `sample 1` 
    - files specific to `build system A` 
    - symlinks to the respective files in `code/sample 1`
  - `sample 2`
    - files specific to `build system A` 
    - symlinks to the respective files in `code/sample 1`
  - `sample ...`
- `build system B`
  - `sample 1`
    - files specific to `build system B`
    - symlinks to the respective files in `code/sample 1`
  - `sample 2` 
    - files specific to `build system B`
    - symlinks to the respective files in `code/sample 2`
  - `sample ...`
- `build system ...`

This way developers can study the sample in the context of their build system of choice and ignore the others. As the same time maintainers and contributors can write code once and test it with multiple build systems.

## Contributions

We try our best to build, test and maintain useful code samples for the leading Liferay Portal based products but that is time and resource consuming process. We would gladly accept contributions to this repository that provide

- new code samples
- bug fixes in existing samples
- optimizations an improvements
- documentation

If you can, and you are willing to contribute to this repository, please do not hesitate to contact the Developer Relations Team (developer-relations@liferay.com)
