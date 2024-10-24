# Lyrics Cloud
By Peter Wang

## Introduction

Songs serve diverse purposes, and their meanings are highly subjective—often depending on the listener's personal experiences and interpretations. Traditionally, we interpret songs primarily by how they sound: we listen to gauge their mood, vibe, and the lyrics sung. We also derive meaning by associating the music with the artist and their persona.

### Purpose

Early song visualizations typically involve rendering moving patterns, images, and waves based on the song's sound waves in real-time. But what if we could enhance this experience by analyzing the lyrics themselves and overlaying additional information while the song plays?

Our project, Lyrics Cloud, aims to collect the most frequently used words from any given song's lyrics and create a Word Cloud as a tool for visualization and song analysis. This visualization can provide listeners with immediate insights into the themes and key messages of the song, potentially deepening their understanding and engagement.

### Intended Audience

This document is primarily aimed at our professor to provide additional context for our team presentation. However, it also serves as an explanation of our program's concept and intent for anyone interested, including potential collaborators and users. See the Stakeholders section.

### Document Conventions

This documentation is written in simple text with minimal formatting. Sections of this document will be utilized on a public GitHub page that will be accessible to others upon the project's formal launch.

## Overall System Description

### Project Background

We are passionate about expanding our understanding of music through innovative technological solutions. With the increasing popularity of music streaming platforms, there is a growing opportunity to enhance user engagement by providing new ways to interact with and analyze music. We aim to expand upon existing capabilities in song analytics by offering a tool that can be valuable to businesses, artists, and listeners alike.

### Project Scope

For this graduate-level course, we will focus on developing a reliable method to extract key data from song lyrics. Our primary goals include:
* Parsing lyrics from a text file.
* Generate a data table containing the word frequencies from the loaded file.
* Creating a Word Cloud visualization based on word frequency using Kumo.
* Uses all available Kumo shapes (Rectangle, Circle, Image Mask)
* Shapes are resized according to configured dimensions
* Exporting the data for further analytical purposes. (TO-DO)

We will also explore other potential applications of this data and develop conceptual mock-ups to illustrate their usefulness in the music industry.

### Out of Scope

At this stage, we do not intend to create a fully integrated solution with existing music streaming platforms. However, we are interested in future collaborations to discuss potential integrations and enhancements.

### Project Objectives

This project aims to add a new dimension to how songs are experienced and understood. By visualizing the most prominent words in a song's lyrics, listeners can gain immediate insights into its themes and messages. This tool can serve as:

* An educational resource for music students and educators.
* A means of enhancing user engagement on music platforms.
* A platform for artists to connect more deeply with their audience.

### How Will This Project Work?

* Lyrics Parsing: The application will accept a text file containing the lyrics of any English song.
* Data Processing: It will process the text to count the frequency of each word, excluding common stop words (e.g., "the," "and," "a").
* Word Cloud Generation: Using the frequency data, the application will generate a Word Cloud where the size of each word corresponds to its frequency in the song.
* Data Export: The frequency data can be exported as a text or CSV file for further analysis or integration with other tools.
 
### Stakeholders

Potential stakeholders include:

* Investors interested in innovative music technology products.
* Music enthusiasts and performers looking for new ways to engage with music.
* Technophiles interested in data visualization and analysis.
* Song analysts and educators who can use this tool for teaching and research purposes.

### Operating Environment

Initially, the application will run in a console or command prompt environment, focusing on functionality and reliability without a graphical user interface (GUI). Future iterations may include:

* Development of a GUI for a more user-friendly experience.
* Network integration to automate song search and lyrics retrieval from online databases.
* Compatibility with various operating systems and platforms.

### Cultural Constraints

While the initial focus is on English-language songs, we recognize the global applicability of this tool. Future versions could support multiple languages, accounting for linguistic nuances and varying stop word lists.

### Legal Constraints

We are aware of potential legal issues surrounding the use of song lyrics, as they are protected by copyright. Our application will require users to provide their own legally obtained lyrics files. In future developments, we will explore licensing agreements or partnerships to legally access lyrics databases.

### Assumptions & Dependencies

* Lyrics Availability: Users will have access to the lyrics of the songs they wish to analyze.
* Database Integration: While we plan to integrate with song and lyric databases eventually, the current version relies on manual input.
* Technical Resources: Access to necessary development tools and resources to build and test the application is assumed.
