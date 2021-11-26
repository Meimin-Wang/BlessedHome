<template>
  <div>
    <h1>实验室成员介绍</h1>
    <lab-member />
    <lab-member />
    <lab-member />
    <lab-member />
  </div>
</template>

<script>
import LabMember from './LabMembersPageComponents/LabMember'
export default {
  name: 'LabMembersPage',
  components: { LabMember },
}
</script>

<style>

</style>
