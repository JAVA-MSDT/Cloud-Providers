## What’s given

To successfully complete course, we’ll need AWS account. Some resources are paid, so credit card with reasonable amount of money should be attached. It’s highly recommended to understand and apply cost controlling settings (you can review full course [here](https://www.linkedin.com/learning/amazon-web-services-controlling-cost/))

## What to save money on

General recommendations (for this course):
- Use minimum sufficient amount of resources. Performance considered only at the concept level
- Shutdown services/instances that you don’t use

Tools that can help:
- [AWS Cost Explorer](https://aws.amazon.com/aws-cost-management/aws-cost-explorer/)
- [Trusted Advisor](https://aws.amazon.com/premiumsupport/technology/trusted-advisor/)
  
## What to do

### Sub-task 1 – Create AWS Account

Follow this [link](https://aws.amazon.com/premiumsupport/knowledge-center/create-and-activate-aws-account/) to create account. Note, accounts are usually activated within a few minutes, but the process might take up to 24 hours.

### Sub-task 2 – Secure account

Follow general AWS recommendations. Here mentioned some of them, but feel free to read full article ([best-practices](https://docs.aws.amazon.com/IAM/latest/UserGuide/best-practices.html)):
- Lock away your AWS account root user access keys ([reference](https://docs.aws.amazon.com/IAM/latest/UserGuide/getting-started_create-admin-group.html))
- Avoid using AWS account root user
- Grant least privilege
- Use permissions with AWS managed policies
- Configure a strong password policy for your users
- Enable MFA

### Sub-task 3 – Set Budgets/Alerts

Avoid surprising charges, so control cost carefully:
- Ensure free tier notifications are enabled ([link](https://docs.aws.amazon.com/awsaccountbilling/latest/aboutv2/tracking-free-tier-usage.html))
- Setup budget reached notifications (ex. 40%, 80%, 100%) manually (via console). Alert should be sent to your email.

_* Optional: Setup Budgets using IaaC (Infrastructure as a Code)_

### _*Sub-task 4 – Optional Task - Setup SCP_

_* Optional Task is not mandatory for completion this module but highly recommended, if you don’t have a time to complete it - just skip it_

Service control policies (SCPs) are a type of organization policy that you can use to manage permissions in your organization. SCPs offer central control over the maximum available permissions for all accounts in your organization.
You can configure the service control policies (SCPs) in your organization to work as either of the following:
- A deny list – actions are allowed by default, and you specify what services and actions are prohibited
- An allow list – actions are prohibited by default, and you specify what services and actions are allowed

Recommended to start from deny list ([link](https://docs.aws.amazon.com/organizations/latest/userguide/orgs_manage_policies_scps_strategies.html#orgs_policies_denylist)). Create organization and new policy. Visual editor will help adding necessary statements. You can review Intro and identify usage of which resources should not be prohibited.

<img src="../assets/01-Intro/SCP.png" alt="Main images car heading twards oracle learning badge">

## What should I remember?

> - Once you create AWS Account -> Setup Multi-factor Authentication
> - Do NOT share your account
> - Do NOT commit your account Credentials into the Git
> - Terminate/Remove all created resources/services once you finishe Module
> - Please Do not forget to delete NAT Gateway if you used it.
> - Do NOT keep instance running if you don’t use it
> - Carefully keep track of billing and working instances so you don't exceed limits
