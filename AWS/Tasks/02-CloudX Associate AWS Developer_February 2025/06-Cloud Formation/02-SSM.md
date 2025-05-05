# Systems Manager

Systems Manager is comprised of individual capabilities, which are grouped into five categories: 

- [Operations Management](#operations-management)
    - [Explorer](#explorer)
    - [OpsCenter](#opscenter)
- [Application Management](#application-management)
    - [Application Manager](#application-manager)
    - [AppConfig](#appconfig)
    - [Parameter Store](#parameter-store)
- [Change Management](#change-management)
    - [Change manager](#change-manager)
    - [Automation](#automation)
    - [Change calendar](#change-calendar)
    - [Maintenance Windows](#maintenance-windows)
- [Node Management](#node-management)
    - [Fleet manager](#fleet-manager)
    - [Compliance](#compliance)
    - [Inventory](#inventory)
    - [Session manager](#session-manager)
    - [Run Command](#run-command)
    - [State Manager](#state-manager)
    - [Patch Manager](#patch-manager)
    - [Distributor](#distributor)
- Shared Resources

### Useful links:
- [What is AWS Systems Manager? (Video)](https://youtu.be/MK4ZoCs-muo)
- [AWS Systems Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/what-is-systems-manager.html)
- [SSM Capabilities](https://docs.aws.amazon.com/systems-manager/latest/userguide/features.html)
- [How SSM works](https://docs.aws.amazon.com/systems-manager/latest/userguide/how-it-works.html)
- [Accessing Systems Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/access-methods.html)
- [Systems Manager prerequisites](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-prereqs.html)
- [AWS Systems Manager Pricing](https://aws.amazon.com/systems-manager/pricing/)


## Operations Management

### Explorer

Explorer is a customizable operations dashboard that reports information about your AWS resources. Explorer displays an aggregated view of operations data (OpsData) for your AWS accounts and across Regions. In Explorer, OpsData includes metadata about your EC2 instances, patch compliance details, and State Manager association compliance details. OpsData also includes information from supporting AWS services like AWS Trusted Advisor, AWS Compute Optimizer, and information about your AWS Support cases.
To raise operational awarness, Explorer also displays operational work items (OpsItems). Explorer provides context about how OpsItems are distributed across your business units or applications, how they trend over time, and how they vary by category. You can group and filter information in Explorer to focus on items that are relevant to you and that require action. When you identify high priority issues, you can use Systems Manager OpsCenter to run Automation runbooks and quickly resolve those issues.

#### Useful links:
- [Explorer](https://docs.aws.amazon.com/systems-manager/latest/userguide/Explorer.html)
- [Getting started with Systems Manager Explorer and OpsCenter](https://docs.aws.amazon.com/systems-manager/latest/userguide/Explorer-setup.html)
- [Using Systems Manager Explorer](https://docs.aws.amazon.com/systems-manager/latest/userguide/Explorer-using.html)
- [Exporting OpsData from Systems Manager Explorer](https://docs.aws.amazon.com/systems-manager/latest/userguide/Explorer-exporting-OpsData.html)
- [Troubleshooting Systems Manager Explorer](https://docs.aws.amazon.com/systems-manager/latest/userguide/Explorer-troubleshooting.html)
- [Get Started with AWS Systems Manager Explorer (video)](https://www.youtube.com/watch?v=XTwv1vSH4h4)
- [How does Explorer works? (video)](https://www.youtube.com/watch?v=2efz7EH4czQ&t=1297)
- [Explorer Pricing](https://aws.amazon.com/systems-manager/pricing/#Explorer)

### OpsCenter

OpsCenter provides a central location where operations engineers and IT professionals can view, investigate, and resolve operational work items (OpsItems) related to AWS resources. OpsCenter is designed to reduce mean time to resolution for issues impacting AWS resources. This Systems Manager capability aggregates and standardizes OpsItems across services while providing contextual investigation data about each OpsItem, related OpsItems, and related resources. OpsCenter also provides Systems Manager Automation documents (runbooks) that you can use to quickly resolve issues. You can specify searchable, custom data for each OpsItem. You can also view automatically-generated summary reports about OpsItems by status and source.
OpsCenter is integrated with Amazon EventBridge and Amazon CloudWatch. This means you can configure these services to automatically create an OpsItem in OpsCenter when a CloudWatch alarm enters the ALARM state or when EventBridge processes an event from any AWS service that publishes events. Configuring CloudWatch alarms and EventBridge events to automatically create OpsItems enables you to quickly diagnose and remediate issues with AWS resources from a single console.

#### Useful links: 
- [OpsCenter](https://docs.aws.amazon.com/systems-manager/latest/userguide/OpsCenter.html)
- [Getting started with OpsCenter](https://docs.aws.amazon.com/systems-manager/latest/userguide/OpsCenter-getting-started.html)
- [Creating OpsItems](https://docs.aws.amazon.com/systems-manager/latest/userguide/OpsCenter-creating-OpsItems.html)
- [Working with OpsItems](https://docs.aws.amazon.com/systems-manager/latest/userguide/OpsCenter-working-with-OpsItems.html)
- [Remediating OpsItem issues using Systems Manager Automation](https://docs.aws.amazon.com/systems-manager/latest/userguide/OpsCenter-remediating.html)
- [Viewing OpsCenter summary reports](https://docs.aws.amazon.com/systems-manager/latest/userguide/OpsCenter-reports.html)
- [Supported resources reference](https://docs.aws.amazon.com/systems-manager/latest/userguide/OpsCenter-related-resources-reference.html)
- [Auditing and logging OpsCenter activity](https://docs.aws.amazon.com/systems-manager/latest/userguide/OpsCenter-logging-auditing.html)
- [Aggregate and Resolve Operational Issues Using AWS Systems Manager OpsCenter (video)](https://www.youtube.com/watch?v=r6ilQdxLcqY)
- [Integrate AWS Systems Manager OpsCenter with Amazon CloudWatch Alarms (video)](https://www.youtube.com/watch?v=Gpc7a5kVakI&t=11s)
- [Integrate Your Data Sources into AWS Systems Manager OpsCenter Using Amazon EventBridge](https://www.youtube.com/watch?v=Xmmu5mMsq3c)
- [OpsCenter Pricing](https://aws.amazon.com/systems-manager/pricing/#OpsCenter)
- [Resource limits for OpsCenter](https://docs.aws.amazon.com/systems-manager/latest/userguide/OpsCenter.html#OpsCenter-learn-more-limits)

## Application Management

### Application Manager

Application Manager, a capability of AWS Systems Manager, helps DevOps engineers investigate and remediate issues with their AWS resources in the context of their applications. Application Manager aggregates operations information from multiple AWS services and Systems Manager capabilities to a single AWS Management Console.
In Application Manager, an application is a logical group of AWS resources that you want to operate as a unit. This logical group can represent different versions of an application, ownership boundaries for operators, or developer environments, to name a few.

After you set up and configure AWS services and Systems Manager capabilities, Application Manager displays the following types of information about your resources:
- Alarms provided by Amazon CloudWatch
- Compliance information provided by AWS Config and Systems Manager State Manager
- Kubernetes cluster information provided by Amazon EKS
- Log data provided by AWS CloudTrail and Amazon CloudWatch Logs
- OpsItems provided by Systems Manager OpsCenter
- Resource details provided by the AWS services that host them.

#### Useful links:
- [Application Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/application-manager.html)
- [Features of Application Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/application-manager.html#application-manager-learn-more-features)
- [Getting started with Systems Manager Application Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/application-manager-getting-started.html)
- [Working with Application Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/application-manager-working.html)
- [Working with Amazon EKS in Application Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/application-manager-working-EKS.html)
- [Working with runbooks](https://docs.aws.amazon.com/systems-manager/latest/userguide/application-manager-working-runbooks.html)
- [Resource quotas for Application Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/application-manager.html#application-manager-learn-more-quotas)

### AppConfig

Use AWS AppConfig, a capability of AWS Systems Manager, to create, manage, and quickly deploy application configurations. You can use AWS AppConfig with applications hosted on Amazon Elastic Compute Cloud (Amazon EC2) instances, AWS Lambda, containers, mobile applications, or IoT devices.

AWS AppConfig use cases:
- Application tuning – Introduce changes carefully to your application that can be tested with production traffic.
- Feature toggle – Turn on new features that require a timely deployment, such as a product launch or announcement.
- Allow list – Allow premium subscribers to access paid content.
- Operational issues – Reduce stress on your application when a dependency or other external factor impacts the system.

#### Useful links:
- [What Is AWS AppConfig?](https://docs.aws.amazon.com/appconfig/latest/userguide/what-is-appconfig.html)
- [Getting started](https://docs.aws.amazon.com/appconfig/latest/userguide/getting-started-with-appconfig.html)
- [Working with AWS AppConfig](https://docs.aws.amazon.com/appconfig/latest/userguide/appconfig-working.html)
- [AWS AppConfig overview (video)](https://youtu.be/ztIxMY3IIu0)
- [Manage and Deploy Application Configurations with AWS AppConfig (video)](https://youtu.be/ztIxMY3IIu0)
- [AppConfig pricing](https://aws.amazon.com/systems-manager/pricing/#AppConfig)
- [Service Quotas](https://docs.aws.amazon.com/general/latest/gr/ssm.html#limits_ssm)
- [Configuration store quotas and limitations](https://docs.aws.amazon.com/appconfig/latest/userguide/appconfig-creating-configuration-and-profile.html#appconfig-creating-configuration-and-profile-quotas)

### Parameter Store

AWS Parameter Store provides secure, hierarchical storage for configuration data management and secrets management. You can store data such as passwords, database strings, Amazon Machine Image (AMI) IDs, and license codes as parameter values. You can store values as plain text or encrypted data. You can reference Systems Manager parameters in your scripts, commands, SSM documents, and configuration and automation workflows by using the unique name that you specified when you created the parameter.

#### Useful links:
- [Parameter Store](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-parameter-store.html)
- [Setting up Parameter Store](https://docs.aws.amazon.com/systems-manager/latest/userguide/parameter-store-setting-up.html)
- [Working with Parameter Store](https://docs.aws.amazon.com/systems-manager/latest/userguide/parameter-store-working-with.html)
- [AWS Systems Manager Parameter Store Introduction (video)](https://www.youtube.com/watch?v=3FUpyzc_raI)
- [AWS Systems Manager Parameter Store Tutorial (video)](https://www.youtube.com/watch?v=zxi3M_rIE2k)
- [AWS Systems Manager Parameter Store | Concept | Demo (video)](https://www.youtube.com/watch?v=lDbq4_6DLxo)
- [Setting up notifications or trigger actions based on Parameter Store events](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-paramstore-cwe.html)
- [Auditing and logging Parameter Store activity](https://docs.aws.amazon.com/systems-manager/latest/userguide/parameter-store-logging-auditing.html)
- [Managing parameter tiers](https://docs.aws.amazon.com/systems-manager/latest/userguide/parameter-store-advanced-parameters.html)
- [Increasing Parameter Store throughput](https://docs.aws.amazon.com/systems-manager/latest/userguide/parameter-store-throughput.html)
- [Parameter Store Pricing](https://aws.amazon.com/systems-manager/pricing/#Parameter_Store)


## Change Management

### Change manager

Change Manager, a capability of AWS Systems Manager, is an enterprise change management framework for requesting, approving, implementing, and reporting on operational changes to your application configuration and infrastructure. From a single delegated administrator account, if you use AWS Organizations, you can manage changes across multiple AWS accounts and across AWS Regions. Alternatively, using a local account, you can manage changes for a single AWS account. Use Change Manager for managing changes to both AWS resources and on-premises resources. With Change Manager, you can use pre-approved change templates to help automate change processes for your resources and help avoid unintentional results when making operational changes.

#### Useful links:
- [Change Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/change-manager.html)
- [Setting up Change Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/change-manager-setting-up.html)
- [Working with Change Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/working-with-change-manager.html)
- [Auditing and logging Change Manager activity](https://docs.aws.amazon.com/systems-manager/latest/userguide/change-manager-auditing.html)
- [Troubleshooting Change Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/change-manager-troubleshooting.html)
- [AWS on Air 2020: AWS Systems Manager Change Manager (video)](https://www.youtube.com/watch?v=1-G4H0l7Tg8)
- [Change Manager pricing](https://aws.amazon.com/systems-manager/pricing/#Change_Manager)
- [Audit Change Manager activity using Amazon CloudWatch alarms](https://docs.aws.amazon.com/systems-manager/latest/userguide/change-manager-auditing.html#change-manager-logging-auditing-alarms)
- [Audit Change Manager activity using AWS CloudTrail](https://docs.aws.amazon.com/systems-manager/latest/userguide/change-manager-auditing.html#change-manager-logging-auditing-cloudtrail)

### Automation

Systems Manager Automation simplifies common maintenance and deployment tasks of Amazon EC2 instances and other AWS resources. Automation enables you to do the following:
- Build automations to configure and manage instances and AWS resources.
- Create custom runbooks or use pre-defined runbooks maintained by AWS.
- Receive notifications about Automation tasks and runbooks by using Amazon EventBridge.
- Monitor Automation progress and details by using the AWS Systems Manager console.

#### Use-cases:

- **Perform common IT tasks** - automation can simplify common IT tasks such as changing the state of one or more instances (using an approval automation) and managing instance states according to a schedule
- **Safely perform disruptive tasks in bulk** - Systems Manager includes features that help you target large groups of instances by using Amazon EC2 tags, and velocity controls that help you roll out changes according to the limits you define.
- **Simplify complex tasks** - Automation offers one-click automations for simplifying complex tasks such as creating golden Amazon Machines Images (AMIs), and recovering unreachable EC2 instances
- **Enhance operations security** - Using delegated administration, you can restrict or elevate user permissions for various types of tasks. Delegated administration enables you to provide permissions for certain tasks on certain resource without having to give a user direct permission to access the resources.
- **Share best practices** - Automation lets you share best practices with the rest of your organization. You can create best practices for resource management in runbooks and easily share the runbooks across AWS Regions and groups

#### Useful links:
- [Create own SSM Document and Execute Automation (Video)](https://www.youtube.com/watch?v=WenPNoKrIgM)
- [Setting up Automation](https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-setup.html)
- [Working with automations](https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-working.html)
- [Systems Manager Automation actions reference](https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-actions.html)
- [Working with runbooks](https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-documents.html)
- [Systems Manager Automation runbook reference](https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-documents-reference.html)
- [Automation walkthroughs](https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-walk.html)
- [Understanding automation statuses](https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-statuses.html)
- [Troubleshooting Systems Manager Automation](https://docs.aws.amazon.com/systems-manager/latest/userguide/automation-troubleshooting.html)
- [Automation pricing](https://aws.amazon.com/systems-manager/pricing/#Automation)
- [Monitoring Systems Manager events with Amazon EventBridge](https://docs.aws.amazon.com/systems-manager/latest/userguide/monitoring-eventbridge-events.html)


### Change calendar

AWS Systems Manager Change Calendar (Change Calendar) lets you set up date and time ranges when actions you specify (for example, in Systems Manager Automation runbooks) may or may not be performed in your AWS account. In Change Calendar, these ranges are called events. When you create a Change Calendar entry, you are creating a Systems Manager document of the type ChangeCalendar. In Change Calendar, the document stores iCalendar 2.0 data in plaintext format. Events that you add to the Change Calendar entry become part of the document.

#### Useful links:
- [Setting up Change Calendar](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-change-calendar-prereqs.html)
- [Working with Change Calendar](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-change-calendar-working.html)
- [Add Change Calendar dependencies to Automation documents](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-change-calendar-automations.html)

### Maintenance Windows

AWS Systems Manager Maintenance Windows let you define a schedule for when to perform potentially disruptive actions on your instances such as patching an operating system, updating drivers, or installing software or patches. Maintenance Windows also lets you schedule actions on numerous other AWS resource types, such as Amazon Simple Storage Service (Amazon S3) buckets, Amazon Simple Queue Service (Amazon SQS) queues, AWS Key Management Service (AWS KMS) keys, and many more. For a full list of supported resource types that you can include in a maintenance window target, see Supported Resources for AWS Resource Groups in the AWS Resource Groups User Guide.

#### Useful links:
- [Setting up Maintenance Windows](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-maintenance-permissions.html)
- [Working with maintenance windows (console)](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-maintenance-working.html)
- [Systems Manager Maintenance Windows tutorials (AWS CLI)](https://docs.aws.amazon.com/systems-manager/latest/userguide/maintenance-windows-tutorials.html)
- [Maintenance window walkthroughs](https://docs.aws.amazon.com/systems-manager/latest/userguide/maintenance-window-walkthroughs.html)
- [Maintenance window scheduling and active period options](https://docs.aws.amazon.com/systems-manager/latest/userguide/maintenance-windows-schedule-options.html)
- [Registering maintenance window tasks without targets](https://docs.aws.amazon.com/systems-manager/latest/userguide/maintenance-windows-targetless-tasks.html)
- [Troubleshooting maintenance windows](https://docs.aws.amazon.com/systems-manager/latest/userguide/troubleshooting-maintenance-windows.html)

## Node Management

### Fleet manager

AWS Systems Manager Fleet Manager (Fleet Manager) is a unified user interface (UI) experience that helps you remotely manage your server fleet running on AWS, or on premises. With Fleet Manager, you can view the health and performance status of your entire server fleet from one console. You can also gather data from individual instances to perform common troubleshooting and management tasks from the console. This includes viewing folder and file contents, Windows registry management, operating system user management, and more.


#### Useful links:
- [Getting started with Fleet Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/fleet-getting-started.html)
- [Working with Fleet Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/fleet-working.html)
- [AWS on Air 2020: AWS What’s Next ft. AWS Systems Manager Fleet Manager](https://www.youtube.com/watch?v=p_FinBFWioI)

### Compliance

You can use AWS Systems Manager Compliance to scan your fleet of managed instances for patch compliance and configuration inconsistencies. You can collect and aggregate data from multiple AWS accounts and Regions, and then drill down into specific resources that aren’t compliant. By default, Compliance displays current compliance data about Systems Manager Patch Manager patching and Systems Manager State Manager associations.
Patch compliance data from Patch Manager can be sent to AWS Security Hub. Security Hub gives you a comprehensive view of your high-priority security alerts and compliance status. It also monitors the patching status of your fleet.

#### Useful links:
- [Getting started with Compliance](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-compliance-prereqs.html)
- [Creating a Resource Data Sync for Compliance](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-compliance-datasync-create.html)
- [Working with Compliance](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-compliance-about.html)
- [Remediating compliance issues using EventBridge](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-compliance-fixing.html)
- [Compliance walkthrough (AWS CLI)](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-compliance-walk.html)


### Inventory

AWS Systems Manager Inventory provides visibility into your Amazon EC2 and on-premises computing environment. You can use Inventory to collect metadata from your managed instances. You can store this metadata in a central Amazon Simple Storage Service (Amazon S3) bucket, and then use built-in tools to query the data and quickly determine which instances are running the software and configurations required by your software policy, and which instances need to be updated. You can configure Inventory on all of your managed instances by using a one-click procedure. You can also configure and view inventory data from multiple AWS Regions and accounts.
If the pre-configured metadata types collected by Systems Manager Inventory don't meet your needs, then you can create custom inventory.

#### Useful links:
- [Learn more about Systems Manager Inventory](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-inventory-about.html)
- [Setting up Systems Manager Inventory](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-inventory-setting-up.html)
- [Configuring inventory collection](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-inventory-configuring.html)
- [Working with Systems Manager inventory data](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-inventory-data-working.html)
- [Working with custom inventory](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-inventory-custom.html)
- [Viewing inventory history and change tracking](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-inventory-history.html)
- [Systems Manager Inventory walkthroughs](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-inventory-walk.html)
- [Troubleshooting problems with Systems Manager Inventory](https://docs.aws.amazon.com/systems-manager/latest/userguide/syman-inventory-troubleshooting.html)
- [Collect Inventory Data with Systems Manager Inventory (video)](https://www.youtube.com/watch?v=T7f-Qi7mR48)

### Session manager

Session Manager is a fully managed AWS Systems Manager capability that lets you manage your Amazon Elastic Compute Cloud (Amazon EC2) instances, on-premises instances, and virtual machines (VMs) through an interactive one-click browser-based shell or through the AWS Command Line Interface (AWS CLI). Session Manager provides secure and auditable instance management without the need to open inbound ports, maintain bastion hosts, or manage SSH keys. Session Manager also makes it easy to comply with corporate policies that require controlled access to instances, strict security practices, and fully auditable logs with instance access details, while still providing end users with simple one-click cross-platform access to your managed instances.
Support for Windows Server, Linux and macOS instances.

#### Useful links:
- [Session Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/execute-remote-commands.html)
- [Setting up Session Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/session-manager-getting-started.html)
- [Working with Session Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/session-manager-working-with.html)
- [Auditing session activity](https://docs.aws.amazon.com/systems-manager/latest/userguide/session-manager-auditing.html)
- [Logging session activity](https://docs.aws.amazon.com/systems-manager/latest/userguide/session-manager-logging.html)
- [Session document schema](https://docs.aws.amazon.com/systems-manager/latest/userguide/session-manager-schema.html)
- [Troubleshooting Session Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/session-manager-troubleshooting.html)
- [Session Manager pricing](https://aws.amazon.com/systems-manager/pricing/#Session_Manager)
- [AWS Basics - SSM - Connect to EC2 Instances with Session Manager (video)](https://www.youtube.com/watch?v=jrYwS0yF3uc)
- [AWS Sessions Manager Overview (video)](https://www.youtube.com/watch?v=cUEFGKaZOyU)
- [Still using SSH on AWS? Check out Session Manager instead! (video)](https://www.youtube.com/watch?v=TiaxgThfTRc)
- [Securely Access Windows Instances Using RDP and AWS Systems Manager Session Manager (video)](https://www.youtube.com/watch?v=nt6NTWQ-h6o)
- [Controlling User Session Access to Instances in AWS System Manager Session Manager (video)](https://www.youtube.com/watch?v=nzjTIjFLiow)

### Run Command

AWS Systems Manager Run Command lets you remotely and securely manage the configuration of your managed instances. A managed instance is any EC2 instance or on-premises machine in your hybrid environment that has been configured for Systems Manager. Run Command enables you to automate common administrative tasks and perform one-time configuration changes at scale. You can use Run Command from the AWS Management Console, the AWS Command Line Interface, AWS Tools for Windows PowerShell, or the AWS SDKs. Run Command is offered at no additional cost.

#### Useful links:
- [Setting up Run Command](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-rc-setting-up.html)
- [Running commands using Systems Manager Run Command](https://docs.aws.amazon.com/systems-manager/latest/userguide/run-command.html)
- [Handling exit codes with scripts](https://docs.aws.amazon.com/systems-manager/latest/userguide/command-workflow-scripts.html)
- [Understanding command statuses](https://docs.aws.amazon.com/systems-manager/latest/userguide/monitor-commands.html)
- [Run Command walkthroughs](https://docs.aws.amazon.com/systems-manager/latest/userguide/run-command-walkthroughs.html)
- [Troubleshooting Systems Manager Run Command](https://docs.aws.amazon.com/systems-manager/latest/userguide/troubleshooting-remote-commands.html)
- [Remotely Run Commands on an EC2 Instance (hands-on)](https://aws.amazon.com/getting-started/hands-on/remotely-run-commands-ec2-instance-systems-manager/)
- [AWS Systems Manager Run Command (video)](https://www.youtube.com/watch?v=PEwgsoXmud8)
- [AWS Systems Manager RUN Command - Execute Command on EC2 Instance (video)](https://www.youtube.com/watch?v=fw01L5Ski-c)
- [Streamline Configuration Management Automation Using AWS Systems Manager Run Command (video)](https://www.youtube.com/watch?v=sriNozVSj0U)
- [Systems Manager service quotas](https://docs.aws.amazon.com/general/latest/gr/ssm.html#limits_ssm)

### State Manager

AWS Systems Manager State Manager (State Manager) is a secure and scalable configuration management service that automates the process of keeping your Amazon Elastic Compute Cloud (Amazon EC2) and hybrid infrastructure in a state that you define.

#### Use-cases:
- Bootstrap instances with specific software at start-up.
- Download and update agents on a defined schedule, including AWS Systems Manager SSM Agent (SSM Agent).
- Configure network settings.
- Join instances to a Windows domain (only Windows Server instances).
- Patch instances with software updates throughout their lifecycle.
- Run scripts on Linux, macOS, and Windows managed instances throughout their lifecycle.

#### Useful links:
- [State Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-state.html)
- [About State Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-state-about.html)
- [Working with associations in Systems Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-associations.html)
- [AWS Systems Manager State Manager walkthroughs](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-state-walk.html)
- [Combating Configuration Drift Using Amazon EC2 Systems Manager and Windows PowerShell DSC](http://aws.amazon.com/blogs/mt/combating-configuration-drift-using-amazon-ec2-systems-manager-and-windows-powershell-dsc/)
- [Configure Amazon EC2 Instances in an Auto Scaling Group Using State Manager](http://aws.amazon.com/blogs/mt/configure-amazon-ec2-instances-in-an-auto-scaling-group-using-state-manager/)
- [Ansible with AWS Systems Manager State Manager (video)](https://www.youtube.com/watch?v=84_vmjiMAwY)

### Patch Manager

AWS Systems Manager Patch Manager automates the process of patching managed instances with both security related and other types of updates. You can use Patch Manager to apply patches for both operating systems and applications. (On Windows Server, application support is limited to updates for Microsoft applications.) You can use Patch Manager to install Service Packs on Windows instances and perform minor version upgrades on Linux instances. You can patch fleets of Amazon Elastic Compute Cloud (Amazon EC2) instances or your on-premises servers and virtual machines (VMs) by operating system type. This includes supported versions of Amazon Linux, Amazon Linux 2, CentOS, Debian Server, macOS, Oracle Linux, Red Hat Enterprise Linux (RHEL), SUSE Linux Enterprise Server (SLES), Ubuntu Server, and Windows Server. You can scan instances to see only a report of missing patches, or you can scan and automatically install all missing patches.

#### Useful links:
- [Patch Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-patch.html)
- [Patch Manager prerequisites](https://docs.aws.amazon.com/systems-manager/latest/userguide/patch-manager-prerequisites.html)
- [How Patch Manager operations work](https://docs.aws.amazon.com/systems-manager/latest/userguide/patch-manager-how-it-works.html)
- [About patching operations](https://docs.aws.amazon.com/systems-manager/latest/userguide/about-patching-operations.html)
- [About patch baselines](https://docs.aws.amazon.com/systems-manager/latest/userguide/about-patch-baselines.html)
- [Remediating out-of-compliance instances (Patch Manager)](https://docs.aws.amazon.com/systems-manager/latest/userguide/patch-compliance-remediation.html)
- [Use Kernel Live Patching on Amazon Linux 2 instances](https://docs.aws.amazon.com/systems-manager/latest/userguide/kernel-live-patching.html)
- [Working with Patch Manager (console)](https://docs.aws.amazon.com/systems-manager/latest/userguide/sysman-patch-working.html)
- [Working with Patch Manager (AWS CLI)](https://docs.aws.amazon.com/systems-manager/latest/userguide/patch-manager-cli-commands.html)
- [AWS Systems Manager Patch Manager walkthroughs](https://docs.aws.amazon.com/systems-manager/latest/userguide/patch-walkthroughs.html)
- [Troubleshooting Patch Manager](https://docs.aws.amazon.com/systems-manager/latest/userguide/patch-manager-troubleshooting.html)
- [Patching for your Amazon EC2 Instances (video)](https://www.youtube.com/watch?v=ABtwRb9BFY4)
- [AWS Systems Manager - Automate Patching for Amazon EC2 Instances (video)](https://www.youtube.com/watch?v=Dm4id0FVhtc)  
- [Patch Manager pricing](https://aws.amazon.com/systems-manager/pricing/#Patch_Manager)

### Distributor

AWS Systems Manager Distributor lets you package your own software—and find AWS-provided agent software packages, such as AmazonCloudWatchAgent, or third-party packages such as Trend Micro—to install on AWS Systems Manager managed instances. Distributor publishes resources, such as software packages, to AWS Systems Manager managed instances. Publishing a package advertises specific versions of the package's document—a Systems Manager document that you create when you add the package in Distributor—to managed instances that you identify by managed instance IDs, AWS account IDs, tags, or an AWS Region.Deploys packages to both Windows and Linux instances

#### Useful links:
- [Distributor](https://docs.aws.amazon.com/systems-manager/latest/userguide/distributor.html)
- [Setting up Distributor](https://docs.aws.amazon.com/systems-manager/latest/userguide/distributor-getting-started.html)
- [Working with Distributor](https://docs.aws.amazon.com/systems-manager/latest/userguide/distributor-working-with.html)
- [Auditing and logging Distributor activity](https://docs.aws.amazon.com/systems-manager/latest/userguide/distributor-logging-auditing.html)
- [Troubleshooting AWS Systems Manager Distributor](https://docs.aws.amazon.com/systems-manager/latest/userguide/distributor-troubleshooting.html)
- [Manage Distribution of Software Packages with AWS Systems Manager Distributor (video)](https://www.youtube.com/watch?v=AvQWkfgEQI8) 
- [Distributor pricing](https://aws.amazon.com/systems-manager/pricing/#Distributor)
- [Supported package platforms and architectures](https://docs.aws.amazon.com/systems-manager/latest/userguide/distributor.html#what-is-a-package)
