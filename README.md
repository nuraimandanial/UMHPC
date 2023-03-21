<h1 align="center"> WIX1002 Final Project Report </h1>

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#group-members-">Group Members</a></li>
    <li><a href="#introduction-">Introductions</a></li>
    <li><a href="#metrics-implemented-">Metrics Implemented</a></li>
    <li><a href="#source-codes-">Source Codes</a></li>
    <li><a href="#outputs-">Outputs</a></li>
    <li><a href="#challenges-faced-">Challenges Faced</a></li>
    <li><a href="#conclusions-%EF%B8%8F">Conclusions</a></li>
  </ol>
</details>

---

## Group Members 👨‍👩‍👧‍👧
<table align="center">
  <thead>
    <tr>
      <th>Name</th>
      <th>Git ID</th>
      <th>Matrics No.</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td align="center">Nuraiman Danial bin Mohd Zaki</td>
      <td align="center">@nuraimandanial</td>
      <td align="center">22004858</td>
    </tr>
    <tr>
      <td align="center">Nerissa Ibrahim</td>
      <td align="center">@nerissa23</td>
      <td align="center">U2101552</td>
    </tr>
    <tr>
      <td align="center">Aysheh Ahmad</td>
      <td align="center">@Aysheh04</td>
      <td align="center">S2187664</td>
    </tr>
  </tbody>
</table>

---

## Introduction 📖
<p align="justify">The ability to solve complicated problems that require broad skills and extensive computational experimentation has been made possible by modern scientific cooperation. These investigations require the use of particular computing platforms to carry out a series of processing stages, or tasks.</p>
 
<p align="justify">An HPC cluster is similar to a regular computer, but much more powerful because calculations are carried out simultaneously over numerous computing nodes. Users submit their calculations, known as jobs, by logging in from their PCs to the HPC cluster login node using the SSH program or a web browser.</p>
 
<p align="justify">An extracted log refers to a log file that has been pulled or extracted from a source, such as a server or application, for the purpose of analysis. Log files can be used for security, monitoring, and troubleshooting since they include information on a system's operations and activities. Utilizing a variety of tools and methods, extracted logs can be examined to learn more about system performance, user behavior, and potential problems.</p>

---

### Codes Taken into Implementation
<p align="center"><img src="https://user-images.githubusercontent.com/116857873/215643360-85d3d879-f572-4e95-8a01-6d94cc1eb1e7.png" height="80%" width="80%">

From the extracted variables from the `extracted_log` file, the yellow highlighted variables were taken as to implement all metrics into one source codes.

---

## Metrics Implemented 📑
![image](https://user-images.githubusercontent.com/116857873/215254024-e9a2dfd5-0007-423f-b926-2e88aee24fe0.png)

---

## Source Codes 📂
<p align="center">The source codes for this project can be found in the following repository: <a href="/OCC9-10/src/Assignment/UMHPC">link to repository</a></p>

![image](https://user-images.githubusercontent.com/116857873/215253665-8daab0b3-8cde-4067-a6f5-4a9868be44de.png)

- <a href="/OCC9-10/src/Assignment/UMHPC/UMHPC.java">UMHPC.java</a> functioned as the main class for output inside the Netbeans terminal.
- <a href="/OCC9-10/src/Assignment/UMHPC/UMHPC_Interface.java">UMHPC_Interface.java</a> functioned as the main class for output in GUI style.

---

## Outputs 💻
### Main Interface
![image](https://user-images.githubusercontent.com/116857873/215253427-ac0d80ae-881d-4009-8a1e-06499b33b43b.png)

<p align="justify">User is given the option to choose whether to retrieve all data collected from the SLURM contoller log file or data particular to selected dates. Graphical representation of selected data will be displayed if the user checks on 'Include Charts'.

---

#### Get All Details
<p align="center"><img src="https://user-images.githubusercontent.com/116857873/215254742-8be564c2-beb3-49fc-b8cb-79fac1521cb1.png" height="50%" width="50%">

<p align="justify">Table displayed when user chooses all data to be extracted from the log file. Statistical data of jobs allocated, completed, killed, exhausted and faced with errors are shown.</p>

#### Get All Details (With Include Charts Selected)
<p align="center"><img src="https://user-images.githubusercontent.com/116857873/215254787-efa0a4ac-a40f-44a8-bbbc-b6a2659525bc.png" height="80%" width="80%">

<p align="justify">Graphical representation of all jobs allocated by partitions (CPU-EPYC, CPU-Opteron & GPU) and the total errors occured with its corresponding users.</p>

---

#### Get Details – Single Date
<p align="center"><img src="https://user-images.githubusercontent.com/116857873/215255059-735b007d-9ccd-4d00-bf95-e5263175311e.png" height="80%" width="80%">

<p align="justify">Table displayed when user inputs one date for the data to be extracted from in yyyy-mm or yyyy-mm-dd format. Average execution time of the jobs allocated are displayed along with other statistical data extracted for that particular date.</p>

#### Get Details – Single Date (With Include Charts Selected)
<p align="center"><img src="https://user-images.githubusercontent.com/116857873/215255135-b54845ee-e2a6-4049-ac3e-a19f2d537209.png" height="80%" width="80%"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/116857873/215255140-8f03cf90-96c9-4515-b6c2-ea3d06e874cd.png" height="80%" width="80%"></p>

<p align="justify">Graphical representation of jobs allocated by partitions, total errors and its corresponding user, status of jobs indicated in the log file and the execution time for every job allocated in that single date.</p>

---

#### Get Details – Ranged Date
<p align="center"><img src="https://user-images.githubusercontent.com/116857873/215255264-9bc79607-df2d-40b6-8ff0-50ffb9ac3cdc.png" height="50%" width="50%"></p>

<p align="justify">Table displayed when user inputs a range of date for the data to be extracted from. Average execution time is displayed along with other statistical data extracted for that particular range of time.</p>

#### Get Details – Ranged Date (With Include Charts Selected)
<p align="center"><img src="https://user-images.githubusercontent.com/116857873/215255368-cd49f51e-3d20-4b5e-9273-9083b32113cd.png" height="80%" width="80%"></p>
<p align="center"><img src="https://user-images.githubusercontent.com/116857873/215255365-3be65b01-dc56-410d-a5ee-5b88872c66ac.png" height="80%" width="80%"></p>

<p align="justify">Graphical representation of jobs allocated by partitions, total errors and its corresponding user, status of jobs indicated in the log file and the execution time for every job allocated in that range of time.

---

## Challenges Faced 📉
![image](https://user-images.githubusercontent.com/117422678/215320461-e8c988b3-4457-4188-90ab-ece55656492f.png)

---

## Conclusions 🏃🏻‍♀️
- Adaptability to new applications (<a href="https://netbeans.org/">NetBeans</a> and <a href="https://github.com/">GitHub</a>) presented a challenge for the project accomplishment.
- Accessibility was difficult due to group members being located off campus
- Time constraints made it hard to study important information and have group discussions
- Despite these challenges, we are able to make progress in learning and utilizing the Slurm workload manager
- We should also focus on collaborating remotely and utilizing online communication tools to stay connected and productive.
- Further training and support may be necessary to fully utilize the capabilities of Slurm workload manager and the related applications.
- We should also identify and prioritize the most important tasks to make the best use of time and resources.

---

<p align="right">(<a href="#-wix1002-final-project-report-">Back to Top</a>)</p>

---
