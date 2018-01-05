# Integration Camel Apis


# RabbitMQ

For install the RabbitMQ you will need install the Erlang first of all.
After that you will download the RabbitMQ installer and execute this.
If when you try install RabbitMQ you receive the message "Insufficient system resources exist to complete the requested service", please follow this steps:

## Fixing RabbitMQ installation

1.Click Start, click Run, type regedit, and then click OK.

2.Locate and then click the following registry subkey:

```
HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Control\Session Manager\Memory Management
```

3.On the Edit menu, point to New, and then click DWORD Value.

4.In the New Value #1 box, type PoolUsageMaximum, and then press ENTER.

5.Right-click PoolUsageMaximum, and then click Modify.

6.In the Value data box, type 60, click Decimal, and then click OK.

7.If the PagedPoolSize registry entry exists, go to step 8. If the PagedPoolSize registry entry does not exist, create it.

To do this, follow these steps:

•On the Edit menu, point to New, and then click DWORD Value.
•In the New Value #1 box, type PagedPoolSize, and then press ENTER.

8.Right-click PagedPoolSize, and then click Modify.

9.In the Value data box, type ffffffff, and then click OK.

10.Exit Registry Editor, and then restart the computer. 

## Enabling the RabbitMQ Management

After the RabbitMQ installation open the RabbitMQ CMD and write this command:

```sh
rabbitmq-plugins enable rabbitmq_management
```

After that use the command:

```sh
rabbitmq-plugins start
```

Your management will be located at: 'http://server-name:15672'.

And the default user which RabbitMQ will create for you is user: 'guest' password: 'guest'