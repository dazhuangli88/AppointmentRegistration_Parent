需要启动的服务
1. docker，启动mongo，redis(mongo防火墙关闭)
2. nginx, 在目录下，双击nginx.exe
3. nacos, 在目录下，打开bin文件夹，双击startup.cmd，输入（单机模式启动）：startup.cmd -m standalone
1. 医院设置后台swagger地址：http://localhost:8003/swagger-ui.html
2. 数据字典后台swagger地址：http://localhost:8002/swagger-ui.html
3. 模拟医院的管理系统地址：http://localhost:9998/

一、项目简介：

该项目实现了一个基于Spring Boot和Vue框架的医院预约挂号系统，也可以叫做看病挂号系统，该系统主要目的有三点，一、给广大患者提供全方位综合的线上医院看病挂号平台，减少患者线下繁琐的看病挂号排队流程，达到让患者实现足不出户口就可以看病挂号的目的，二、为医院减少了窗口的人工操作时间，给医院的管理提高了工作效率，优化了医院的资源利用和对数据的管理。该系统包括管理者操作的后端管理系统和患者使用的前端患者系统。

在医院后端管理系统方面,可以对五个组件进行操作，分别有医院营运、数据治理、病患监护、订单处理、统计分析。医院的营运组件包括医院目录的设置、查阅等操作；数据治理组件包括数据字表的目录树形显示、数据的录入和数据的输出；患者监护组件包括患者目录、对患者资料进行验证审核等的操作。订单处理组件包括患者的订单目录的操作；统计分析组件包括计算患者看病挂号量总和的的操作。

在医院前端患者系统方面，系统提供了首页医院的资料显示，医院导航栏检索、患者访问、患者实名身份验证、患者档案、看病挂号和看病提醒功能。患者可以通过手机号进行访问，进行实名身份资料验证后便可以管理病患资料并进行看病挂号的操作，支付医院订单并接收看病通知。

二、相关技术

框架：SpringBoot+springcloud+MybatisPlus+Nacos+Feign+Gateway

环境部署：
Docker容器部署，使用Docker Compose来定义并管理整个系统的容器化部署，包括MongoDB、Redis和消息队列服务等，这种部署方式能够简化部署流程、提高环境一致性，并实现系统的快速扩展和自动化管理。

 数据库：
MySQL存储医院相关数据：
在医院看病挂号系统中，MySQL可以用于存储医院的各种数据，包括数据字表、订单、支付、挂号、医院设置等信息，确保数据一致性和完整性，以及支持数据查询和分析等功能。

MongoDB存储医院相关数据：
存储医院的各种数据，包括医院、科室、轮值班次等信息。使用MongoDB的事务支持来保证数据的一致性和完整性。

Redis缓存管理：
在系统中配置Redis缓存管理，设置适当的缓存过期时间，本系统中使用Redis来存储校验码和支付二维码，并设置合适的过期时间，确保安全性和及时性，减轻对MongoDB的访问压力

RabbitMQ处理订单相关操作：
定义订单相关的消息队列，例如订单创建、支付状态更新等。在订单相关操作时，发送相应的消息到RabbitMQ队列，实现异步处理订单。

 整合阿里云平台服务：
将患者实名验证中上传的文件（如身份证号、照片等）存储到云对象存储中，在系统中集成云对象存储的SDK，实现文件的上传和下载功能。

云平台短信服务：使用云平台短信服务发送患者访问校验码、看病提醒等短信通知，在系统中集成云平台短信服务的SDK，实现短信发送功能。

 EasyExcel：
在看病挂号系统的后端技术中，EasyExcel可以用于处理电子表格文件的读取和写入操作，实现对系统中的省、市、区、学历等数据导入导出。这样可以提高系统的灵活性和扩展性，使系统具备更强的数据处理能力。

项目构建工具：
Maven

项目管理工具：
Git

三、功能模块

系统的功能组件划分，可以有助于更好的理解系统的功能范围和需求，下面将介绍医院看病挂号系统的主要功能需求。

（1）医院后端管理者系统：
医院营运组件：包括医院目录的设置、医院资料、医生的轮值班次、医院的上下线等功能。
数据治理组件：包括数据字表的目录树形显示、数据的录入和数据的输出。
病患监护组件：包括患者目录、查阅、锁住及去锁、对患者档案进行验证审核等功能。
订单处理组件：包括患者的订单目录和订单资料的功能。
统计分析组件：统计患者看病挂号量的功能。

（2）医院前端患者系统：
首页数据显示：展示首页医院的资料显示、医院导航栏检索。
患者访问：包括手机号访问功能。
患者实名验证：患者进行实名身份验证的功能
病患档案：管理患者资料的功能。
看病挂号：包括能查阅医生轮值班次和对应科室资料、确认患者挂号资料、产生患者挂号订单、付款订单和取消患者挂号订单并发送提醒患者看病时间和相关信息等功能。

四、项目结构


├─common
│  ├─common_util
│  │  ├─src
│  │  │  ├─main
│  │  │  │  ├─java
│  │  │  │  │  └─com
│  │  │  │  │      └─fugui
│  │  │  │  │          └─AppointmentRegistration
│  │  │  │  │              └─common
│  │  │  │  │                  ├─exception
│  │  │  │  │                  ├─helper
│  │  │  │  │                  ├─result
│  │  │  │  │                  └─utils
│  │  │  │  └─resources
│  │  │  └─test
│  │  │      └─java
│  │  └─target
│  │      ├─classes
│  │      │  └─com
│  │      │      └─fugui
│  │      │          └─AppointmentRegistration
│  │      │              └─common
│  │      │                  ├─exception
│  │      │                  ├─helper
│  │      │                  ├─result
│  │      │                  └─utils
│  │      ├─generated-sources
│  │      │  └─annotations
│  │      ├─generated-test-sources
│  │      │  └─test-annotations
│  │      ├─maven-archiver
│  │      └─maven-status
│  │          └─maven-compiler-plugin
│  │              ├─compile
│  │              │  └─default-compile
│  │              └─testCompile
│  │                  └─default-testCompile
│  ├─rabbit_util
│  │  ├─src
│  │  │  ├─main
│  │  │  │  ├─java
│  │  │  │  │  └─com
│  │  │  │  │      └─fugui
│  │  │  │  │          └─AppoointmentRegistration
│  │  │  │  │              └─common
│  │  │  │  │                  └─rabbit
│  │  │  │  │                      ├─config
│  │  │  │  │                      ├─constant
│  │  │  │  │                      └─service
│  │  │  │  └─resources
│  │  │  └─test
│  │  │      └─java
│  │  └─target
│  │      ├─classes
│  │      │  └─com
│  │      │      └─fugui
│  │      │          └─AppoointmentRegistration
│  │      │              └─common
│  │      │                  └─rabbit
│  │      │                      ├─config
│  │      │                      ├─constant
│  │      │                      └─service
│  │      └─generated-sources
│  │          └─annotations
│  └─service_util
│      ├─src
│      │  ├─main
│      │  │  ├─java
│      │  │  │  └─com
│      │  │  │      └─fugui
│      │  │  │          └─AppoointmentRegistration
│      │  │  │              └─common
│      │  │  │                  ├─config
│      │  │  │                  ├─helper
│      │  │  │                  └─util
│      │  │  └─resources
│      │  └─test
│      │      └─java
│      └─target
│          ├─classes
│          │  └─com
│          │      └─fugui
│          │          └─AppoointmentRegistration
│          │              └─common
│          │                  ├─config
│          │                  ├─helper
│          │                  └─util
│          ├─generated-sources
│          │  └─annotations
│          └─maven-status
│              └─maven-compiler-plugin
│                  └─compile
│                      └─default-compile
├─hospital-manage
│  ├─.mvn
│  │  └─wrapper
│  ├─src
│  │  ├─main
│  │  │  └─java
│  │  │      ├─com
│  │  │      │  └─fugui
│  │  │      │      └─hospital
│  │  │      │          ├─config
│  │  │      │          ├─controller
│  │  │      │          ├─mapper
│  │  │      │          ├─model
│  │  │      │          ├─service
│  │  │      │          │  └─impl
│  │  │      │          └─util
│  │  │      └─resources
│  │  │          ├─static
│  │  │          │  ├─css
│  │  │          │  │  ├─demo
│  │  │          │  │  ├─layout
│  │  │          │  │  │  └─meta
│  │  │          │  │  ├─patterns
│  │  │          │  │  ├─plugins
│  │  │          │  │  │  ├─awesome-bootstrap-checkbox
│  │  │          │  │  │  ├─blueimp
│  │  │          │  │  │  │  ├─css
│  │  │          │  │  │  │  └─img
│  │  │          │  │  │  ├─bootstrap-table
│  │  │          │  │  │  ├─chosen
│  │  │          │  │  │  ├─clockpicker
│  │  │          │  │  │  ├─codemirror
│  │  │          │  │  │  ├─colorpicker
│  │  │          │  │  │  │  ├─css
│  │  │          │  │  │  │  └─img
│  │  │          │  │  │  │      └─bootstrap-colorpicker
│  │  │          │  │  │  ├─cropper
│  │  │          │  │  │  ├─datapicker
│  │  │          │  │  │  ├─dataTables
│  │  │          │  │  │  ├─dropzone
│  │  │          │  │  │  ├─footable
│  │  │          │  │  │  │  └─fonts
│  │  │          │  │  │  ├─fullcalendar
│  │  │          │  │  │  ├─iCheck
│  │  │          │  │  │  ├─images
│  │  │          │  │  │  ├─ionRangeSlider
│  │  │          │  │  │  ├─jasny
│  │  │          │  │  │  ├─jqgrid
│  │  │          │  │  │  ├─jsTree
│  │  │          │  │  │  ├─markdown
│  │  │          │  │  │  ├─morris
│  │  │          │  │  │  ├─nouslider
│  │  │          │  │  │  ├─plyr
│  │  │          │  │  │  ├─simditor
│  │  │          │  │  │  ├─steps
│  │  │          │  │  │  ├─summernote
│  │  │          │  │  │  ├─sweetalert
│  │  │          │  │  │  ├─switchery
│  │  │          │  │  │  ├─toastr
│  │  │          │  │  │  ├─treeview
│  │  │          │  │  │  └─webuploader
│  │  │          │  │  ├─xietong
│  │  │          │  │  └─xuetong
│  │  │          │  ├─fonts
│  │  │          │  ├─img
│  │  │          │  └─js
│  │  │          │      ├─common
│  │  │          │      ├─plugins
│  │  │          │      │  ├─datePicker
│  │  │          │      │  │  ├─lang
│  │  │          │      │  │  └─skin
│  │  │          │      │  │      ├─default
│  │  │          │      │  │      └─whyGreen
│  │  │          │      │  ├─layer
│  │  │          │      │  │  ├─extend
│  │  │          │      │  │  └─skin
│  │  │          │      │  │      ├─default
│  │  │          │      │  │      └─moon
│  │  │          │      │  ├─metisMenu
│  │  │          │      │  ├─pace
│  │  │          │      │  ├─slimscroll
│  │  │          │      │  ├─validate
│  │  │          │      │  └─ztree
│  │  │          │      │      ├─css
│  │  │          │      │      │  ├─awesomeStyle
│  │  │          │      │      │  │  └─img
│  │  │          │      │      │  ├─metroStyle
│  │  │          │      │      │  │  └─img
│  │  │          │      │      │  └─zTreeStyle
│  │  │          │      │      │      └─img
│  │  │          │      │      │          └─diy
│  │  │          │      │      └─js
│  │  │          │      └─sd_js
│  │  │          └─templates
│  │  │              ├─common
│  │  │              ├─department
│  │  │              ├─frame
│  │  │              ├─hospital
│  │  │              ├─hospitalSet
│  │  │              ├─schedule
│  │  │              └─static
│  │  │                  ├─css
│  │  │                  │  ├─demo
│  │  │                  │  ├─layout
│  │  │                  │  │  └─meta
│  │  │                  │  ├─patterns
│  │  │                  │  ├─plugins
│  │  │                  │  │  ├─awesome-bootstrap-checkbox
│  │  │                  │  │  ├─blueimp
│  │  │                  │  │  │  ├─css
│  │  │                  │  │  │  └─img
│  │  │                  │  │  ├─bootstrap-table
│  │  │                  │  │  ├─chosen
│  │  │                  │  │  ├─clockpicker
│  │  │                  │  │  ├─codemirror
│  │  │                  │  │  ├─colorpicker
│  │  │                  │  │  │  ├─css
│  │  │                  │  │  │  └─img
│  │  │                  │  │  │      └─bootstrap-colorpicker
│  │  │                  │  │  ├─cropper
│  │  │                  │  │  ├─datapicker
│  │  │                  │  │  ├─dataTables
│  │  │                  │  │  ├─dropzone
│  │  │                  │  │  ├─footable
│  │  │                  │  │  │  └─fonts
│  │  │                  │  │  ├─fullcalendar
│  │  │                  │  │  ├─iCheck
│  │  │                  │  │  ├─images
│  │  │                  │  │  ├─ionRangeSlider
│  │  │                  │  │  ├─jasny
│  │  │                  │  │  ├─jqgrid
│  │  │                  │  │  ├─jsTree
│  │  │                  │  │  ├─markdown
│  │  │                  │  │  ├─morris
│  │  │                  │  │  ├─nouslider
│  │  │                  │  │  ├─plyr
│  │  │                  │  │  ├─simditor
│  │  │                  │  │  ├─steps
│  │  │                  │  │  ├─summernote
│  │  │                  │  │  ├─sweetalert
│  │  │                  │  │  ├─switchery
│  │  │                  │  │  ├─toastr
│  │  │                  │  │  ├─treeview
│  │  │                  │  │  └─webuploader
│  │  │                  │  ├─xietong
│  │  │                  │  └─xuetong
│  │  │                  ├─fonts
│  │  │                  ├─img
│  │  │                  └─js
│  │  │                      ├─common
│  │  │                      ├─plugins
│  │  │                      │  ├─datePicker
│  │  │                      │  │  ├─lang
│  │  │                      │  │  └─skin
│  │  │                      │  │      ├─default
│  │  │                      │  │      └─whyGreen
│  │  │                      │  ├─layer
│  │  │                      │  │  ├─extend
│  │  │                      │  │  └─skin
│  │  │                      │  │      ├─default
│  │  │                      │  │      └─moon
│  │  │                      │  ├─metisMenu
│  │  │                      │  ├─pace
│  │  │                      │  ├─slimscroll
│  │  │                      │  ├─validate
│  │  │                      │  └─ztree
│  │  │                      │      ├─css
│  │  │                      │      │  ├─awesomeStyle
│  │  │                      │      │  │  └─img
│  │  │                      │      │  ├─metroStyle
│  │  │                      │      │  │  └─img
│  │  │                      │      │  └─zTreeStyle
│  │  │                      │      │      └─img
│  │  │                      │      │          └─diy
│  │  │                      │      └─js
│  │  │                      └─sd_js
│  │  └─test
│  │      └─java
│  │          └─com
│  │              └─fugui
│  │                  └─hospitalmanage
│  └─target
│      ├─classes
│      │  ├─com
│      │  │  └─fugui
│      │  │      └─hospital
│      │  │          ├─config
│      │  │          ├─controller
│      │  │          ├─mapper
│      │  │          ├─model
│      │  │          ├─service
│      │  │          │  └─impl
│      │  │          └─util
│      │  ├─resources
│      │  ├─static
│      │  │  ├─css
│      │  │  │  ├─demo
│      │  │  │  ├─layout
│      │  │  │  │  └─meta
│      │  │  │  └─plugins
│      │  │  │      ├─blueimp
│      │  │  │      │  ├─css
│      │  │  │      │  └─img
│      │  │  │      ├─chosen
│      │  │  │      ├─clockpicker
│      │  │  │      ├─codemirror
│      │  │  │      ├─colorpicker
│      │  │  │      │  └─css
│      │  │  │      ├─cropper
│      │  │  │      ├─datapicker
│      │  │  │      ├─dataTables
│      │  │  │      ├─dropzone
│      │  │  │      ├─footable
│      │  │  │      │  └─fonts
│      │  │  │      ├─iCheck
│      │  │  │      ├─images
│      │  │  │      ├─jasny
│      │  │  │      ├─jqgrid
│      │  │  │      ├─jsTree
│      │  │  │      ├─markdown
│      │  │  │      ├─morris
│      │  │  │      ├─nouslider
│      │  │  │      ├─plyr
│      │  │  │      ├─simditor
│      │  │  │      ├─steps
│      │  │  │      ├─summernote
│      │  │  │      ├─sweetalert
│      │  │  │      ├─switchery
│      │  │  │      ├─toastr
│      │  │  │      └─treeview
│      │  │  └─js
│      │  │      ├─common
│      │  │      ├─plugins
│      │  │      │  ├─datePicker
│      │  │      │  │  ├─lang
│      │  │      │  │  └─skin
│      │  │      │  │      ├─default
│      │  │      │  │      └─whyGreen
│      │  │      │  ├─layer
│      │  │      │  │  ├─extend
│      │  │      │  │  └─skin
│      │  │      │  │      ├─default
│      │  │      │  │      └─moon
│      │  │      │  ├─metisMenu
│      │  │      │  ├─pace
│      │  │      │  ├─slimscroll
│      │  │      │  ├─validate
│      │  │      │  └─ztree
│      │  │      │      ├─css
│      │  │      │      │  ├─awesomeStyle
│      │  │      │      │  │  └─img
│      │  │      │      │  ├─metroStyle
│      │  │      │      │  │  └─img
│      │  │      │      │  └─zTreeStyle
│      │  │      │      │      └─img
│      │  │      │      │          └─diy
│      │  │      │      └─js
│      │  │      └─sd_js
│      │  └─templates
│      │      ├─common
│      │      ├─department
│      │      ├─frame
│      │      ├─hospital
│      │      ├─hospitalSet
│      │      ├─schedule
│      │      └─static
│      │          ├─css
│      │          │  ├─patterns
│      │          │  ├─plugins
│      │          │  │  ├─awesome-bootstrap-checkbox
│      │          │  │  ├─bootstrap-table
│      │          │  │  ├─colorpicker
│      │          │  │  │  └─img
│      │          │  │  │      └─bootstrap-colorpicker
│      │          │  │  ├─fullcalendar
│      │          │  │  ├─ionRangeSlider
│      │          │  │  └─webuploader
│      │          │  ├─xietong
│      │          │  └─xuetong
│      │          ├─fonts
│      │          └─img
│      ├─generated-sources
│      │  └─annotations
│      ├─generated-test-sources
│      │  └─test-annotations
│      └─test-classes
│          └─com
│              └─fugui
│                  └─hospitalmanage
├─model
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─fugui
│  │  │  │          └─AppointmentRegistration
│  │  │  │              ├─config
│  │  │  │              ├─enums
│  │  │  │              ├─model
│  │  │  │              │  ├─acl
│  │  │  │              │  ├─base
│  │  │  │              │  ├─cmn
│  │  │  │              │  ├─cms
│  │  │  │              │  ├─hosp
│  │  │  │              │  ├─order
│  │  │  │              │  └─user
│  │  │  │              └─vo
│  │  │  │                  ├─acl
│  │  │  │                  ├─cmn
│  │  │  │                  ├─hosp
│  │  │  │                  ├─msm
│  │  │  │                  ├─order
│  │  │  │                  └─user
│  │  │  └─resources
│  │  └─test
│  │      └─java
│  └─target
│      ├─classes
│      │  └─com
│      │      └─fugui
│      │          └─AppointmentRegistration
│      │              ├─config
│      │              ├─enums
│      │              ├─model
│      │              │  ├─acl
│      │              │  ├─base
│      │              │  ├─cmn
│      │              │  ├─cms
│      │              │  ├─hosp
│      │              │  ├─order
│      │              │  └─user
│      │              └─vo
│      │                  ├─acl
│      │                  ├─cmn
│      │                  ├─hosp
│      │                  ├─msm
│      │                  ├─order
│      │                  └─user
│      ├─generated-sources
│      │  └─annotations
│      ├─generated-test-sources
│      │  └─test-annotations
│      ├─maven-archiver
│      ├─maven-status
│      │  └─maven-compiler-plugin
│      │      ├─compile
│      │      │  └─default-compile
│      │      └─testCompile
│      │          └─default-testCompile
│      └─test-classes
├─service
│  ├─service_cmn
│  │  ├─src
│  │  │  ├─main
│  │  │  │  ├─java
│  │  │  │  │  └─com
│  │  │  │  │      └─fugui
│  │  │  │  │          └─AppointmentRegistration
│  │  │  │  │              └─cmn
│  │  │  │  │                  ├─controller
│  │  │  │  │                  ├─listener
│  │  │  │  │                  ├─mapper
│  │  │  │  │                  └─service
│  │  │  │  │                      └─impl
│  │  │  │  └─resources
│  │  │  └─test
│  │  │      └─java
│  │  │          └─com
│  │  │              └─fugui
│  │  │                  └─easyexcel
│  │  └─target
│  │      ├─classes
│  │      │  └─com
│  │      │      └─fugui
│  │      │          └─AppointmentRegistration
│  │      │              └─cmn
│  │      │                  ├─controller
│  │      │                  ├─listener
│  │      │                  ├─mapper
│  │      │                  └─service
│  │      │                      └─impl
│  │      ├─generated-sources
│  │      │  └─annotations
│  │      ├─generated-test-sources
│  │      │  └─test-annotations
│  │      └─test-classes
│  │          └─com
│  │              └─fugui
│  │                  └─easyexcel
│  ├─service_hospital
│  │  ├─src
│  │  │  ├─main
│  │  │  │  ├─java
│  │  │  │  │  ├─com
│  │  │  │  │  │  └─fugui
│  │  │  │  │  │      └─AppointmentRegistration
│  │  │  │  │  │          └─hosipital
│  │  │  │  │  │              ├─controller
│  │  │  │  │  │              │  └─api
│  │  │  │  │  │              ├─mapper
│  │  │  │  │  │              │  └─xml
│  │  │  │  │  │              ├─receiver
│  │  │  │  │  │              ├─repository
│  │  │  │  │  │              └─service
│  │  │  │  │  │                  └─impl
│  │  │  │  │  └─xml
│  │  │  │  └─resources
│  │  │  └─test
│  │  │      └─java
│  │  └─target
│  │      ├─classes
│  │      │  ├─com
│  │      │  │  └─fugui
│  │      │  │      └─AppointmentRegistration
│  │      │  │          └─hosipital
│  │      │  │              ├─controller
│  │      │  │              │  └─api
│  │      │  │              ├─mapper
│  │      │  │              │  └─xml
│  │      │  │              ├─receiver
│  │      │  │              ├─repository
│  │      │  │              └─service
│  │      │  │                  └─impl
│  │      │  └─xml
│  │      └─generated-sources
│  │          └─annotations
│  ├─service_msm
│  │  ├─src
│  │  │  ├─main
│  │  │  │  ├─java
│  │  │  │  │  └─com
│  │  │  │  │      └─fugui
│  │  │  │  │          └─AppointmentRegistration
│  │  │  │  │              └─msm
│  │  │  │  │                  ├─controller
│  │  │  │  │                  ├─receiver
│  │  │  │  │                  ├─service
│  │  │  │  │                  │  └─impl
│  │  │  │  │                  └─utils
│  │  │  │  └─resources
│  │  │  └─test
│  │  │      └─java
│  │  └─target
│  │      ├─classes
│  │      │  └─com
│  │      │      └─fugui
│  │      │          └─AppointmentRegistration
│  │      │              └─msm
│  │      │                  ├─controller
│  │      │                  ├─receiver
│  │      │                  ├─service
│  │      │                  │  └─impl
│  │      │                  └─utils
│  │      └─generated-sources
│  │          └─annotations
│  ├─service_order
│  │  ├─src
│  │  │  ├─main
│  │  │  │  ├─java
│  │  │  │  │  └─com
│  │  │  │  │      └─fugui
│  │  │  │  │          └─AppointmentRegistration
│  │  │  │  │              └─order
│  │  │  │  │                  ├─api
│  │  │  │  │                  ├─config
│  │  │  │  │                  ├─controller
│  │  │  │  │                  ├─mapper
│  │  │  │  │                  ├─receiver
│  │  │  │  │                  ├─service
│  │  │  │  │                  │  └─impl
│  │  │  │  │                  └─utils
│  │  │  │  └─resources
│  │  │  │      ├─cert
│  │  │  │      └─xml
│  │  │  └─test
│  │  │      └─java
│  │  └─target
│  │      ├─classes
│  │      │  ├─com
│  │      │  │  └─fugui
│  │      │  │      └─AppointmentRegistration
│  │      │  │          └─order
│  │      │  │              ├─api
│  │      │  │              ├─config
│  │      │  │              ├─controller
│  │      │  │              ├─mapper
│  │      │  │              ├─receiver
│  │      │  │              ├─service
│  │      │  │              │  └─impl
│  │      │  │              └─utils
│  │      │  └─xml
│  │      └─generated-sources
│  │          └─annotations
│  ├─service_oss
│  │  ├─src
│  │  │  ├─main
│  │  │  │  ├─java
│  │  │  │  │  └─com
│  │  │  │  │      └─fugui
│  │  │  │  │          └─AppointmentRegistration
│  │  │  │  │              └─oss
│  │  │  │  │                  ├─controller
│  │  │  │  │                  ├─service
│  │  │  │  │                  │  └─impl
│  │  │  │  │                  └─utils
│  │  │  │  └─resources
│  │  │  └─test
│  │  │      └─java
│  │  └─target
│  │      ├─classes
│  │      │  └─com
│  │      │      └─fugui
│  │      │          └─AppointmentRegistration
│  │      │              └─oss
│  │      │                  ├─controller
│  │      │                  ├─service
│  │      │                  │  └─impl
│  │      │                  └─utils
│  │      └─generated-sources
│  │          └─annotations
│  ├─service_statistics
│  │  ├─src
│  │  │  ├─main
│  │  │  │  ├─java
│  │  │  │  │  └─com
│  │  │  │  │      └─fugui
│  │  │  │  │          └─AppointmentRegistration
│  │  │  │  │              └─statistics
│  │  │  │  │                  └─controller
│  │  │  │  └─resources
│  │  │  └─test
│  │  │      └─java
│  │  └─target
│  │      ├─classes
│  │      │  └─com
│  │      │      └─fugui
│  │      │          └─AppointmentRegistration
│  │      │              └─statistics
│  │      │                  └─controller
│  │      └─generated-sources
│  │          └─annotations
│  ├─service_task
│  │  ├─src
│  │  │  ├─main
│  │  │  │  ├─java
│  │  │  │  │  └─com
│  │  │  │  │      └─fugui
│  │  │  │  │          └─AppointmentRegistration
│  │  │  │  │              └─task
│  │  │  │  │                  └─scheduled
│  │  │  │  └─resources
│  │  │  └─test
│  │  │      └─java
│  │  └─target
│  │      ├─classes
│  │      │  └─com
│  │      │      └─fugui
│  │      │          └─AppointmentRegistration
│  │      │              └─task
│  │      │                  └─scheduled
│  │      └─generated-sources
│  │          └─annotations
│  └─service_user
│      ├─src
│      │  └─main
│      │      ├─java
│      │      │  ├─com
│      │      │  │  └─fugui
│      │      │  │      └─AppointmentRegistration
│      │      │  │          └─user
│      │      │  │              ├─api
│      │      │  │              ├─config
│      │      │  │              ├─controller
│      │      │  │              ├─mapper
│      │      │  │              │  └─xml
│      │      │  │              └─service
│      │      │  │                  └─impl
│      │      │  └─test
│      │      │      └─java
│      │      └─resources
│      └─target
│          ├─classes
│          │  └─com
│          │      └─fugui
│          │          └─AppointmentRegistration
│          │              └─user
│          │                  ├─api
│          │                  ├─config
│          │                  ├─controller
│          │                  ├─mapper
│          │                  │  └─xml
│          │                  └─service
│          │                      └─impl
│          └─generated-sources
│              └─annotations
├─service_client
│  ├─servicer_client_order
│  │  ├─src
│  │  │  ├─main
│  │  │  │  ├─java
│  │  │  │  │  └─com
│  │  │  │  │      └─fugui
│  │  │  │  │          └─AppointmentRegistration
│  │  │  │  │              └─order
│  │  │  │  │                  └─client
│  │  │  │  └─resources
│  │  │  └─test
│  │  │      └─java
│  │  └─target
│  │      ├─classes
│  │      │  └─com
│  │      │      └─fugui
│  │      │          └─AppointmentRegistration
│  │      │              └─order
│  │      │                  └─client
│  │      └─generated-sources
│  │          └─annotations
│  ├─service_client_cmn
│  │  ├─src
│  │  │  ├─main
│  │  │  │  ├─java
│  │  │  │  │  └─com
│  │  │  │  │      └─fugui
│  │  │  │  │          └─AppointmentRegistration
│  │  │  │  │              └─cmn
│  │  │  │  │                  └─client
│  │  │  │  └─resources
│  │  │  └─test
│  │  │      └─java
│  │  └─target
│  │      ├─classes
│  │      │  └─com
│  │      │      └─fugui
│  │      │          └─AppointmentRegistration
│  │      │              └─cmn
│  │      │                  └─client
│  │      └─generated-sources
│  │          └─annotations
│  ├─service_client_hosp
│  │  ├─src
│  │  │  ├─main
│  │  │  │  ├─java
│  │  │  │  │  └─com
│  │  │  │  │      └─fugui
│  │  │  │  │          └─AppointmentRegistration
│  │  │  │  │              └─hosp
│  │  │  │  │                  └─client
│  │  │  │  └─resources
│  │  │  └─test
│  │  │      └─java
│  │  └─target
│  │      ├─classes
│  │      │  └─com
│  │      │      └─fugui
│  │      │          └─AppointmentRegistration
│  │      │              └─hosp
│  │      │                  └─client
│  │      └─generated-sources
│  │          └─annotations
│  └─service_client_user
│      ├─src
│      │  ├─main
│      │  │  ├─java
│      │  │  │  └─com
│      │  │  │      └─fugui
│      │  │  │          └─AppointmentRegistration
│      │  │  │              └─user
│      │  │  │                  └─client
│      │  │  └─resources
│      │  └─test
│      │      └─java
│      └─target
│          ├─classes
│          │  └─com
│          │      └─fugui
│          │          └─AppointmentRegistration
│          │              └─user
│          │                  └─client
│          └─generated-sources
│              └─annotations
├─service_gateway
│  ├─src
│  │  ├─main
│  │  │  ├─java
│  │  │  │  └─com
│  │  │  │      └─fugui
│  │  │  │          └─AppointmentRegistration
│  │  │  │              └─gateway
│  │  │  │                  ├─config
│  │  │  │                  └─filter
│  │  │  └─resources
│  │  └─test
│  │      └─java
│  └─target
│      ├─classes
│      │  └─com
│      │      └─fugui
│      │          └─AppointmentRegistration
│      │              └─gateway
│      │                  ├─config
│      │                  └─filter
│      └─generated-sources
│          └─annotations





common：放了公用的工具类，包括子工具类rabbit_util、service_util

hospital-mange：调用的医院接口

model：实体类

service-cmn：数据字表，用来操作数据

service_hosp:医院、医院设置、医院科室、轮值班次服务

service_msm:发送短信服务

service_order:订单服务

service_oss:患者文件上传功能

service_statistics:统计数据

service_task:服务提醒功能

service_user:患者服务

五、项目实现截图

前台实现：

（1）医院首页显示功能
在系统前端患者页面中，由医院等级、查询地区、医院目录、医院名称模糊查这四部分组成，在后端数据字表中可根据父级id查询到到子级id，因此患者可根据需要挑选对应的医院等级联动查询地区，查询地区后便会有相对应的医院目录资料展示，患者也可以在搜索栏中搜索需要查询的医院名字，根据病因挑选医院对应科室、医生的轮值班次进行看病挂号，如图4-1前端首页图所示。

![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/60d32a25-d8bb-46b8-9d62-6e3425ff37f7)

（2）前台患者访问功能 手机号被患者输入，等待接收验证码。收到验证码后，填入相应区域，随后点击访问按键即可完成访问，如图4-2访问图所示。

![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/825fe4ad-b4a5-4f74-a97c-078a32a23e6c)

（3）前端实名验证功能

患者提交实名身份验证然后填写个人资料并上传证件照后等待后端管理者进行身份审核，如图4-3前端实名验证图所示。
![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/894cdd01-6ab7-4b6c-97fe-7421508291c8)

（4）前端病患档案功能

患者访问病患档案页面，查阅是否显示所有患者资料，在患者资料页面中患者可以自由修订已有的患者资料，选定一个患者后进行资料修订并保存。可以搜索患
者资料，输入关键词并确认搜索，如图4-4病患档案图、图4-5患者资料图所示。
![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/2120cb26-4691-4d93-9848-474c4d8ad775)

![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/a1ccd1cc-74f4-4bcc-a973-3adbf2a03c85)

（5）前端看病挂号详情功能

患者浏览医院目录和医院资料，根据病因挑选看病的医院和对应科室再根据科室挑选看病的医生和预约时间，确认挂号信息后提交看病挂号订单，如图4-6医院资料图、图4-7看病挂号图所示。

![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/7d2b3afc-2967-4b92-aee4-dfd9fa411955)
![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/472bbf76-ce19-4a80-9d76-a992df4b8fd4)

（6）前端微信支付功能

患者选择看病挂号订单后，能够顺利访问微信支付界面，如图4-8微信支付图所示。
![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/d18d2bcd-4854-42c8-932a-67e4c04e34ed)

（7）前端挂号订单功能

患者可以访问订单资料页面可以显示正确的订单资料，包括患者名字、医院、科室、医生挂号费用、订单状态以及具体看病时间等，患者可以使用模糊查询功能查询患者挂号订单资料，如图4-9挂号订单图所示。
![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/e3e1db12-d151-45b2-8468-5d012354729d)

（8）前端取消预约功能

患者在点选取消预约后，能够顺利完成取消流程，患者取消看病挂号后订单状态会正确更新为已取消，如图4-10取消预约图所示。
![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/7909d694-6b43-48d6-988c-1976cb8d477f)

后台实现：

（1）医院营运功能

管理者可以使用账号访问后端管理系统并对医院营运组件进行操作，管理者可以查阅医院的目录包括已存在医院的医院称谓、医院编号、医院接口路径和手机号等资料，并且可以点选增加按键填写医院相关资料并进行保存，完成对医院相关资料的增加；对于已存在的医院可以对其进行修订和移除的操作，修订医院资料或移除医院记录也可以查阅医院资料，包括可以查阅医院称谓、联络渠道、位置、医院介绍、预约规则、交通方式、医院的科室资料所对应的医生轮值班次等资料；管理者可以下线医院，暂时关闭医院的看病挂号功能，但保留医院资料和医生轮值班次，如图4-16医院营运图、图4-17增加医院图、图4-18医院目录图、图4-19查阅医院资料图、图4-20医生轮值班次所示图。
![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/17d224e5-a690-4e98-ba3d-c09dbe3b1808)
![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/c3b9cdbe-4b6e-4010-a9c0-4bf31df03cfe)
![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/1186a364-c233-4911-8c6a-ab70b689103a)
![image](https://github.com/dazhuangli88/AppointmentRegistration_Parent/assets/138795186/f254e65d-9ba1-4a70-911d-68f99f02653f)

（2）后端数据治理功能

管理者可以操作数据治理组件，可以查阅数据字表的树形结构。他们可以单击“录入数据”按键从数据源录入所需的数据。他们可以单击“输出数据”按键将数据输出到电子表格格式文件，如图4-21数据字表图、图4-22录入图、图4-23输出图所示。

（3）后端病患监护功能

管理人员可以操作病患监护组件，在患者目录中，可以对待审核的患者进行审核操作，批准或拒绝。点选查阅患者资料按键后，可以查阅患者已通过审核上传的患者资料。此外，管理人员还具备执行锁住或去锁患者的权限，如图4-24患者目录、图4-25患者查阅、图4-26验证审核列表所示。

（4）后端统计分析功能

管理者可以操作统计分析组件，在预约统计中查阅到医院挂号量统计图，如图4-27预约统计图所示。

（5）后端订单处理功能

管理者可以操作订单处理组件，在订单目录中查看订单资料并对患者看病挂号订单进行查询操作，如图4-28订单列表图所示

















