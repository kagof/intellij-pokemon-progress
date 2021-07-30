# Contributing Guidelines

[![GitHub contributors](https://img.shields.io/github/contributors/kagof/intellij-pokemon-progress)](https://github.com/kagof/intellij-pokemon-progress/graphs/contributors)

Thank you for your interest in contributing to the Pokémon Progress Bar IntelliJ extension! Please read, understand, and agree to the following before making your contribution. Please also ensure you have read and agreed to the [Code of Conduct](CODE_OF_CONDUCT.md).

If you have any issues or questions, please, do not hesitate to contact [the maintainer](https://github.com/kagof) of this plugin.

## Workflow

The workflow for this project is pretty standard:

```none
open issue and have it assigned to you
            │
            v
    fork repository
            │
            v
create feature/{issueNumber} or 
{fix/issueNumber} branch based off
   of the develop branch
            |
            v
    commit to fix issue
            │
            v
open pull request to develop branch
            │
            v
       code review<────────┐
            │   │          │
            │   └─> address feedback
            v
approved, merged, issue closed
            |
            v
release branch created from develop
    by project maintainer
            |
            v
release branch merged to master
            │
            v
         deployed
```

## Bug Reports

Please make sure all bug reports have not already been reported or fixed, and come with a clear description of the situation, effect, expected experience, and, if at all possible, steps to reproduce the bug.

## Feature Requests

Please make sure all feature requests are clear, concise, feasible, useful, and not already implemented or requested.

## Pull Requests

We encourage pull requests for bugs or features, but please open an issue first and ensure it has been discussed & approved by the maintainer before beginning work. Your code will be reviewed as soon as possible; please be willing to accept feedback and change your pull request as needed.

Also ensure that the extension still runs properly after your changes, by using the built in extension debugger in IntelliJ.

Ideally, we'd like to work with a branch-per-issue policy, as well as a one-commit-per-issue policy. Feel free to make a separate commit when addressing code review comments, or to amend your existing commit. If new commits are made, the maintainer may squash them into the original before merging.

Preferably you should be using [signed commits](https://help.github.com/en/articles/signing-commits), although this is not required.

### Additional PR Information

* This plugin is written using Java 11 and the IntelliJ SDK.
* Please also do your best to follow the existing code style. You may be asked to refactor your code if it does not match the existing style, in the interest of consistency.
* please branch off of [develop](https://github.com/kagof/intellij-pokemon-progress/tree/develop), and open pull requests to that branch as well, unless doing an "urgent" bugfix (to be decided by the maintainer), in which case PRs should be based off of [master](https://github.com/kagof/intellij-pokemon-progress/tree/master).
* if adding a new Pokémon:
    * please maintain numerical ordering in the [Readme](README.md), [plugin.xml](src/main/resources/META-INF/plugin.xml), and [Pokemon.java](src/main/java/com/kagof/intellij/plugins/pokeprogress/Pokemon.java)
    * [editSprite.sh](editSprite.sh) can be used to generate the required gifs from existing png images
    * [DocumentGenerator.java](src/test/java/com/kagof/intellij/plugins/pokeprogress/DocumentationGenerator.java) can be used to generate the new lines in [`README.md`](README.md), and update the [family photo](eg/family.png) used in both the README and [`description.html`](./description.html). Simply run the `updateReadme()` and `updateFamilyPicture()` tests to do so.
    * [TestProgressBar.java](src/test/java/com/kagof/intellij/plugins/pokeprogress/TestProgressBar.java)) is very useful when tweaking sprite positioning & sizing (Thanks to @Paola351 for the initial implementation of this!)