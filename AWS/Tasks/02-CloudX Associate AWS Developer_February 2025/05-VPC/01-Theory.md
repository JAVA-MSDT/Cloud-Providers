# VPC - A virtual network dedicated to your AWS account.


## Table of Content:

- [Your Goals](#your-goals)
- [Subnets and CIDR](#subnets-and-cidr)
- [VPC Overview](#vpc-overview)
- [Elastic Network Interfaces](#elastic-network-interfaces-enis)
- [Elastic IP Addresses](#elastic-ip-addresses-eips)
- [NAT Instances and NAT Gateways](#network-address-translation-nat-instances-and-nat-gateways)
- [VPC Endpoint](#vpc-endpoint)
- [VPC Peering](#vpc-peering)
- [Transit Gateway (TGW)](#transit-gateway-tgw)
- [VPC Flow Logs](#vpc-flow-logs)
- [Route 53](#route-53)
- [Self-check](#self-check)
- [Hands-on Labs](#hands-on-labs)
- [More details](#more-details)

## Your Goals:

- To get familiar with AWS Network Architecture components: VPC, Subnets, Private/Public subnets layout, Nat Gateways, Routing Tables, Elastic Network Interfaces(ENI)
- To know how to establish Cross VPC connectivity: VPC Peering, Transit Gateway
- To know how to secure your Networks (NACL and Security Groups, the difference between them)
- To get familiar with AWS DNS Service (AmazonProvidedDNS), DHCPs Option Sets
- Understand Route53: Public/Private Zones, Types of Records (including CNAME and Aliases)
- Learn about AWS CloudFront(Content Delivery Network)


## Subnets and CIDR

Please, read the following article that you should understand before doing anything with VPC: [Understanding IP Addresses, Subnets, and CIDR Notation for Networking](https://www.digitalocean.com/community/tutorials/understanding-ip-addresses-subnets-and-cidr-notation-for-networking).

## VPC Overview

![](https://blog.gelin.ru/2018/06/01%20VPC,%20subnets.png)

Amazon Virtual Private Cloud (Amazon VPC) lets you provision a logically isolated section of the Amazon Web Services (AWS) Cloud where you can launch AWS resources in a virtual network that you define. You have complete control over your virtual networking environment, including selection of your own IP address range, creation of subnets, and configuration of route tables and network gateways

Read [Amazon Virtual Private Cloud ](VPC.md) to get more details.

## Elastic Network Interfaces (ENIs)

![](images/2.4.png)

An Elastic Network Interface (ENI) is a virtual network interface that you can attach to an instance in an Amazon VPC. ENIs are only available within an Amazon VPC, and they are associated with a subnet upon creation. They can have one public IP address and multiple private IP addresses. If there are multiple private IP addresses, one of them is primary. Assigning a second network interface to an instance via an ENI allows it to be dual-homed (have network presence in different subnets). An ENI created independently of a particular instance persists regardless of the lifetime of any instance to which it is attached; if an underlying instance fails, the IP address may be preserved by attaching the ENI to a replacement instance.

ENIs allow you to create a management network, use network and security appliances in your Amazon VPC, create dual-homed instances with workloads/roles on distinct subnets, or create a low-budget, high-availability solution.

## IPv6
You can optionally associate an IPv6 CIDR block with your VPC and subnets, and assign IPv6 addresses from that block to the resources in your VPC. IPv6 addresses are public and reachable over the internet. Your VPC can operate in dual-stack mode: your resources can communicate over IPv4, or IPv6, or both. IPv4 and IPv6 addresses are independent of each other; you must configure routing and security in your VPC separately for IPv4 and IPv6.

### Use cases / Considerations
- A common use case for ENIs is the creation of management networks. This allows you to have public-facing applications like web servers in a public subnet but lock down SSH access down to a private subnet on a secondary network interface. In this scenario, you would connect using a VPN to the private management subnet, then administrate your servers as usual.
- ENIs are also often used as the primary network interfaces for Docker containers launched on ECS using Fargate. This allows Fargate tasks to handle complex networking, set firewalls in place using security groups, and be launched into private subnets.


#### Docs:

[IP Addressing in your VPC](https://docs.aws.amazon.com/vpc/latest/userguide/vpc-ip-addressing.html)

## Elastic IP Addresses (EIPs)

![](images/2.5.png)

AWS maintains a pool of public IP addresses in each region and makes them available for you to associate to resources within your Amazon VPCs. An Elastic IP Addresses (EIP) is a static, public IP address in the pool for the region that you can allocate to your account (pull from the pool) and release (return to the pool). EIPs allow you to maintain a set of IP addresses that remain fixed while the underlying infrastructure may change over time. Here are the important points to understand about EIPs:

- You must first allocate an EIP for use within a VPC and then assign it to an instance.
- EIPs are specific to a region (that is, an EIP in one region cannot be assigned to an instance within an Amazon VPC in a different region).
- There is a one-to-one relationship between network interfaces and EIPs.
- You can move EIPs from one instance to another, either in the same Amazon VPC or a different Amazon VPC within the same region.
- EIPs remain associated with your AWS account until you explicitly release them.
- There are charges for EIPs allocated to your account, even when they are not associated with a resource.

### Use cases / Considerations
- AWS Elastic IPs are useful in scenarios where you need a fixed public IP address that can be associated with and disassociated from instances on demand.
- Elastic IPs can be used to mask the failure of an instance by quickly remapping the address to another instance in the same Availability Zone.

### Pricing considerations

- [Amazon Elastic IP Addresses pricing ](https://repost.aws/knowledge-center/elastic-ip-charges)

## Network Address Translation (NAT) Instances and NAT Gateways

By default, any instance that you launch into a private subnet in an Amazon VPC is not able to communicate with the Internet through the IGW. This is problematic if the instances within private subnets need direct access to the Internet from the Amazon VPC in order to apply security updates, download patches, or update application software. AWS provides NAT instances and NAT gateways to allow instances deployed in private subnets to gain Internet access. For common use cases, we recommend that you use a NAT gateway instead of a NAT instance. The NAT gateway provides better availability and higher bandwidth, and requires
less administrative effort than NAT instances.

For more information, see [NAT Instances and NAT Gateways](NAT.md).

## VPC endpoint

A VPC endpoint enables you to privately connect your VPC to supported AWS services and VPC endpoint services powered by PrivateLink without requiring an internet gateway, NAT device, VPN connection, or AWS Direct Connect connection. Instances in your VPC do not require public IP addresses to communicate with resources in the service. Traffic between your VPC and the other service does not leave the Amazon network.

For more information, see [VPC Endpoint](VPC-Enpoint.md)

## VPC Peering

An Amazon VPC peering connection is a networking connection between two Amazon VPCs that enables instances in either Amazon VPC to communicate with each other as if they are within the same network. You can create an Amazon VPC peering connection between your own Amazon VPCs or with an Amazon VPC in another AWS account within a single region. A peering connection is neither a gateway nor an Amazon VPN connection and does not introduce a single point of failure for communication.

For more information, see [VPC Peering](VPC-Peering.md)

## Transit Gateway (TGW)
With large number of VPCs, Transit Gateway provides simpler VPC-to-VPC communication management over VPC Peering. Note that the transit hub can be used to interconnect not only our VPCs but also on-premises networks.

![](images/2.14.png)

The Transit Gateway provides a hub and spoke design for connecting VPCs and on-premises networks as a fully managed service without VPN overlay.

![](images/2.13.png)

<<<<<<< HEAD
## Use cases / Considerations
- __Deliver applications around the world.__ Build, deploy, and manage applications across thousands of Amazon VPCs without having to manage peering connections or update routing tables.
- __Rapidly move to global scale.__ Share VPCs, Domain Name System (DNS), Microsoft Active Directory, and IPS/IDS across Regions with inter-Region peering.
- __Smoothly respond to spikes in demand.__ Quickly add Amazon VPCs, AWS accounts, virtual private networking (VPN) capacity, or AWS Direct Connect gateways to meet unexpected demand.
- __Host multicast applications on AWS.__ Host multicast applications that scale based on demand, without the need to buy and maintain custom hardware.
=======
### Pricing considerations

- [Amazon Transit Gateway pricing](https://aws.amazon.com/transit-gateway/pricing/)
>>>>>>> 6fbdc97 (Added pricing for modules)

See [documentation](https://docs.aws.amazon.com/vpc/latest/tgw/what-is-transit-gateway.html) to get more details.

## VPC Flow Logs

VPC Flow Logs is a feature that enables you to capture information about the IP traffic going to and from network interfaces in your VPC. Flow log data can be published to Amazon CloudWatch Logs or Amazon S3. After you've created a flow log, you can retrieve and view its data in the chosen destination.  

### Pricing considerations

Flow logs can publish flow log data directly to Amazon CloudWatch. Data ingestion and archival charges for vended logs apply when you publish flow logs to CloudWatch Logs.

- [Amazon VPC Flow Log pricing](https://docs.aws.amazon.com/vpc/latest/userguide/flow-logs.html#flow-logs-pricing)

For more information, see [VPC Flow Logs](FlowLogs.md).

## Route 53

Amazon Route 53 is a highly available and scalable cloud Domain Name System (DNS) web service. It is designed to give developers and businesses an extremely reliable and cost effective way to route end users to Internet applications by translating names like www.example.com into the numeric IP addresses like 192.0.2.1 that computers use to connect to each other. Amazon Route 53 is fully compliant with IPv6 as well.

Amazon Route 53 effectively connects user requests to infrastructure running in AWS – such as Amazon EC2 instances, Elastic Load Balancing load balancers, or Amazon S3 buckets – and can also be used to route users to infrastructure outside of AWS. You can use Amazon Route 53 to configure DNS health checks, then continuously monitor your applications’ ability to recover from failures and control application recovery with Route 53 Application Recovery Controller.

## Use cases / Considerations
- __Traffic Flow.__ Amazon Route 53 provides Easy-to-use & cost-effective global traffic management that is route end users to the best endpoint for their application based on the proximity, latency, health, and other considerations. Thus Amazon Route 53 is widely used across the world.
- __Latency based Routing.__ Amazon Route 53 provides Latency Based Routing (LBR) which is AWS’s highly reliable cost-effective DNS service. The Latency Based Routing which is one of the Amazon Route 53’s most requested features, helps users to improve their application’s performance for the global audience. Amazon Route 53 Latency Based Routing works by routing user's customers to the AWS endpoint (e.g. EC2 instances, Elastic IPs or ELBs) which provides the fastest experience based on actual performance measurements of the different AWS regions where the user's application is running.
- __Geo DNS.__ Amazon Route 53 enables users to purchase a new domain name or transfer the management of their existing domain name to Route 53. When users purchase the new domains via Route 53, the service will automatically configure a Hosted Zone for each domain. Amazon Route 53 offers the privacy protection for user's WHOIS records at no additional charge. In addition, users benefit from AWS's consolidated billing to manage their domain name expenses alongside all of their other AWS resources. Amazon Route 53 offers a selection of more than 150 top-level domains (TLDs), including the major generic TLDs.
- __Private DNS for Amazon VPC.__ Amazon Route 53 provides private DNS for Amazon VPC(Virtual Private Cloud) which helps in managing custom domain names for users' internal AWS resources without further exposing the DNS data to the public Internet.

More information about Amazon Route 53:
- [Route 53: Developer Guide](https://docs.aws.amazon.com/Route53/latest/DeveloperGuide/Welcome.html)
Video:
- [AWS re:Invent 2017: DNS Demystified: Global Traffic Management with Amazon Route 53 (NET302)](https://www.youtube.com/watch?v=PVBC1gb78r8&t=163s)
- [AWS re:Invent 2016: DNS Demystified: Amazon Route 53, featuring Warner Bros. (NET202)](https://www.youtube.com/watch?v=AAq-DDbFiIE)
- [DNS Design Using Amazon Route 53 - AWS Online Tech Talks](https://www.youtube.com/watch?v=2y_RBjDkRgY)

### Pricing considerations

- [Amazon Route 53 pricing](https://aws.amazon.com/route53/pricing/)

## Self-check:

1. What kinds of IP addresses does AWS VPC offer?
2. What happens to different IP address types when an EC2 instance is rebooted, stopped, started? 
3. Can you assign multiple IP addresses to an instance?
4. How many elastic IPs is it possible to create per account/region?
5. What's the difference between Public and Private subnet?
6. How do VPC subnets map onto AZs?
7. What's the difference between Default and Custom VPC?
8. What is the difference between NAT gateways and NAT instances?
9. Suppose you’ve assigned a CIDR block to a VPC. Will all the IPs in that block be available for the resources you create in the VPC?
10. When will you use NACL and when will you use SG? Which of them is stateful and which one is stateless? 
11. How does VPC determine whether to deny/allow a request when both ACLs and security groups specified?
12. What does "local" target mean in terms of an AWS routing table?
13. How do you connect your VPC to the Internet?
14. What is bastion in terms of networking?
15. How can you monitor the network traffic in VPC?
16. Can you peer VPC with a VPC belonging to another AWS account?
17. Do you pay for VPC when using EC2 instances?

## Hands-on Labs:
  [Get the hands-on experience with basic and advanced labs](./hands-on-labs.md)

## More details:

### Videos:

- [Amazon Virtual Private Cloud (VPC) | AWS Tutorial For Beginners](https://www.youtube.com/embed/fpxDGU2KdkA)
- [AWS VPC & Subnets | Amazon Web Services BASICS](https://www.youtube.com/watch?v=bGDMeD6kOz0)
- [AWS Networking Fundamentals](https://www.youtube.com/watch?v=hiKPPy584Mg)
- [AWS VPC | re:Invent 2015](https://www.youtube.com/watch?v=3qln2u1Vr2E)
- [VPC Flow Logs | What is Flow Logs?](https://www.youtube.com/watch?v=_A5L4jT-K9I)
- [Setup VPC Flow Logs To CloudWatch Log Group](https://www.youtube.com/watch?v=aczp7gCZZlQ)
- VPC Basics(RUS): [VPC - Lecture](https://www.youtube.com/watch?v=zPjVWiPpT-8), [VPC - Practice](https://www.youtube.com/watch?v=sA9We2Gl4eI), [VPC - Bastion Host](https://www.youtube.com/watch?v=vTER05sRObI), [VPC - Peering, VPN, FlowLogs](https://www.youtube.com/watch?v=dlIvxxydShU), [AWS - VPC Demo, Public & Private Subnets, Route Tables, Internet & NAT Gateways](https://www.youtube.com/watch?v=tD9vDv0uyI8&ab_channel=KnowledgeIndiaAWSAzureTutorials)


### Documentation:

- [Private Addresses RFC1918](https://tools.ietf.org/html/rfc1918)
- [Prefixes](https://www.ripe.net/about-us/press-centre/understanding-ip-addressing)
- [VPC Limits](https://docs.aws.amazon.com/vpc/latest/userguide/amazon-vpc-limits.html)
- [Publishing flow logs to CloudWatch Logs](https://docs.aws.amazon.com/vpc/latest/userguide/flow-logs-cwl.html)
- [Publishing flow logs to Amazon S3](https://docs.aws.amazon.com/vpc/latest/userguide/flow-logs-s3.html)
- [Troubleshooting VPC Flow Logs](https://docs.aws.amazon.com/vpc/latest/userguide/flow-logs-troubleshooting.html)
- [VPC Scenarios](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_Scenarios.html)
- [VPC FAQ](https://aws.amazon.com/ru/vpc/faqs/?nc=sn&loc=5)
- [AWS Routing 101](https://medium.com/@mda590/aws-routing-101-67879d23014d)
- [What is bastion?](https://aws.amazon.com/ru/quickstart/architecture/linux-bastion/)

### Recommended Trainings:

- [AWS: Networking](https://learn.epam.com/detailsPage?id=2699c11b-d1c8-455a-bf5e-1af537ba363c&source=EXTERNAL_COURSE)
- [AWS Certified Solutions Architect - Associate (SAA-C02): 3 Virtual Private Cloud](https://learn.epam.com/detailsPage?id=b29ebc21-f056-4436-a748-8b7ce12efbd1&source=EXTERNAL_COURSE)
- [AWS Certified Solutions Architect - Associate (SAA-C02): 6 Auto Scaling and Virtual Network Services](https://learn.epam.com/detailsPage?id=93b5715a-7725-4456-8034-a897287b5787&source=EXTERNAL_COURSE)

## Optional: [Materials for AWS Certification Exam](./certification_networking.md)
