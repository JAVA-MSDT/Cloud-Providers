## What’s given
- A personal AWS account was created, and billing alerts were set up.

## What to do

### Sub-task 1 – Create 3 User Groups
Let’s imagine that we have created an AWS Account that will use all members of our AWS program(Coordinator, Mentees, Mentors(Experts)). For all these users it will be better to create different groups with different permissions because for example Coordinator has more permissions than Mentee. Please create 3 user groups:
- CoordinatorsGroup
- MentorsGroup
- MenteesGroup

### Sub-task 2 – Create policies and roles
1. Create a policy named FullAccessPolicyEC2.
2. Configure the FullAccessPolicyEC2 to allow any actions on the EC2 resources.
3. Similarly, create policies for S3:
    - FullAccessPolicyS3 – everything’s allowed.
    - ReadAccessPolicyS3 – only get and list actions.
4. Create one role of EC2 Type (Trusted Entity) per each policy configured so far (note – these roles won’t be used right now, but might be reused in the upcoming EC2 module):
    - FullAccessRoleEC2
    - FullAccessRoleS3
    - ReadAccessRoleS3
5. Create one group per each policy configured so far:
    - FullAccessGroupEC2
    - FullAccessGroupS3
    - ReadAccessGroupS3
6. Create 1 user from the 1st group, 1 user from the 2nd group, and 1 user from the 3rd group.
7. Add inline policy for user1 for write access to S3. The inline policy is a policy created for a single IAM identity (a user, group, or role). Inline policies maintain a strict one-to-one relationship between a policy and an identity. For more information please see https://docs.aws.amazon.com/IAM/latest/UserGuide/access_policies_managed-vs-inline.html 
8. Install the latest version of the AWS CLI. Please follow this guide https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html. 
9. Configure named profiles for each user from the step #6 to be used with AWS CLI in the subsequent modules. For more info please see https://docs.aws.amazon.com/cli/latest/userguide/cli-configure-profiles.html

## What should I remember?
> - Once you create AWS Account -> Setup Multi-factor Authentication
> - Do NOT share your account
> - Do NOT commit your account Credentials into the Git
> - Terminate/Remove all created resources/services once you finish Module
> - Please Do not forget to delete NAT Gateway if you used it.
> - Do NOT keep instance running if you don’t use it
> - Carefully keep track of billing and working instances so you don't exceed limits 
