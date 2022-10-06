<!Doctype html>
<html>
<head>
  <title>Mybatis Insert</title>
  <link rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <style>
    .container {
      margin-top: 10px;
      max-width: 750px;
    }
  </style>
</head>
<body>
<div class="container">

    <#if response??>
      <div style="border: 1px solid black;border-radius: 5px 5px 5px 5px;">
          <#if response.httpStatus.value() == 200>
            <b>Employee saved !</b>
          </#if>
          <#if response.httpStatus.value() != 200>
            <b>Employee not saved status: ${response.httpStatus.value()}</b>
          </#if>
        <ul>
            <#list response.validationErrors as error>
              <li> ${error.message}</li>
            </#list>
        </ul>
      </div>
    </#if>
  <h5>Add New Employee</h5>
  <form action="/update" method="post">
    Name:<br/>
    <input type="text" class="form-control" name="name" value="${emp.name}" required/>
    <br/>
    Phone:<br/>
    <input type="text" class="form-control" name="phone" value="${emp.phone}" required/>
    <br/>
    Email:<br/>
    <input type="text" class="form-control" name="email" value="${emp.email}" required/>
    <br/>
    Branch:<br/>
    <input type="text" class="form-control" name="branch" value="${emp.branch}" required/>
    <br/>
    <input type="hidden" name="id" value="${emp.id}"/>
    <button type="submit" class="btn btn-primary">Submit</button>
  </form>
</div>
</body>
</html>