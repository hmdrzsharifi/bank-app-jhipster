import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router-dom';
import { Modal, ModalHeader, ModalBody, ModalFooter, Button } from 'reactstrap';
import { Translate, ICrudGetAction, ICrudDeleteAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { ISavingsAccountTransaction } from 'app/shared/model/savings-account-transaction.model';
import { IRootState } from 'app/shared/reducers';
import { getEntity, deleteEntity } from './savings-account-transaction.reducer';

export interface ISavingsAccountTransactionDeleteDialogProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const SavingsAccountTransactionDeleteDialog = (props: ISavingsAccountTransactionDeleteDialogProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const handleClose = () => {
    props.history.push('/savings-account-transaction');
  };

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const confirmDelete = () => {
    props.deleteEntity(props.savingsAccountTransactionEntity.id);
  };

  const { savingsAccountTransactionEntity } = props;
  return (
    <Modal isOpen toggle={handleClose}>
      <ModalHeader toggle={handleClose}>
        <Translate contentKey="entity.delete.title">Confirm delete operation</Translate>
      </ModalHeader>
      <ModalBody id="bankApp.savingsAccountTransaction.delete.question">
        <Translate contentKey="bankApp.savingsAccountTransaction.delete.question" interpolate={{ id: savingsAccountTransactionEntity.id }}>
          Are you sure you want to delete this SavingsAccountTransaction?
        </Translate>
      </ModalBody>
      <ModalFooter>
        <Button color="secondary" onClick={handleClose}>
          <FontAwesomeIcon icon="ban" />
          &nbsp;
          <Translate contentKey="entity.action.cancel">Cancel</Translate>
        </Button>
        <Button id="jhi-confirm-delete-savingsAccountTransaction" color="danger" onClick={confirmDelete}>
          <FontAwesomeIcon icon="trash" />
          &nbsp;
          <Translate contentKey="entity.action.delete">Delete</Translate>
        </Button>
      </ModalFooter>
    </Modal>
  );
};

const mapStateToProps = ({ savingsAccountTransaction }: IRootState) => ({
  savingsAccountTransactionEntity: savingsAccountTransaction.entity,
  updateSuccess: savingsAccountTransaction.updateSuccess,
});

const mapDispatchToProps = { getEntity, deleteEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(SavingsAccountTransactionDeleteDialog);
